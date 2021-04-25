package com.sparta.infrastructure.rest.controller;

import com.sparta.infrastructure.client.DataClientFactory;
import com.sparta.infrastructure.db.memory.repository.RecordMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class MainControllerIT {

    public static final String PROVIDER = "provider";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RecordMemoryRepository memoryRepository;

    @BeforeEach
    void setup() throws Exception {
        purgeMessageStore();
    }

    @Test
    void loadEndPoint_WhenCalled_ThenTotalRecordSentReturned() throws Exception {
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/load/{provider}", PROVIDER)
                .content(createRecords())
                .accept(MediaType.ALL))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo("100");

    }

    @Test
    void total_WhenCalled_ThenTotalMessagesProvidersReturned() throws Exception {
        final MvcResult result = mvc.perform(MockMvcRequestBuilders
                .get("/data/{provider}/total", PROVIDER)
                .accept(MediaType.ALL))
                .andReturn();

        assertThat(result.getResponse().getContentAsString())
                .isEqualTo("0");
    }

    @Test
    void loadAndRead_WhenCalledTwoTimes_ThenTotalMessagesProvidersReturned() throws Exception {
        final MvcResult firstLoad = mvc.perform(MockMvcRequestBuilders
                .post("/load/{provider}", PROVIDER)
                .content(createRecords())
                .accept(MediaType.ALL))
                .andReturn();

        assertThat(firstLoad.getResponse().getContentAsString())
                .isEqualTo("100");

        final MvcResult secondLoad = mvc.perform(MockMvcRequestBuilders
                .post("/load/{provider}", PROVIDER)
                .content(createRecords())
                .accept(MediaType.ALL))
                .andReturn();

        assertThat(secondLoad.getResponse().getContentAsString())
                .isEqualTo("100");

        final MvcResult messages = mvc.perform(MockMvcRequestBuilders
                .get("/data/{provider}/total", PROVIDER)
                .accept(MediaType.ALL))
                .andReturn();

        assertThat(messages.getResponse().getContentAsString())
                .isEqualTo("200");
    }

    private void purgeMessageStore() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Method method = memoryRepository.getClass().getDeclaredMethod("purgeMessageStore");
        method.setAccessible(true);
        method.invoke(memoryRepository);
    }

    private byte[] createRecords() throws IOException {
        DataClientFactory dataFactory = new DataClientFactory();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        dataFactory.write(baos, 100);
        return baos.toByteArray();
    }

}
