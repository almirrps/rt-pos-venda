package com.teste.rtposvenda.feign;

import com.teste.rtposvenda.feign.client.PostClient;
import com.teste.rtposvenda.feign.dto.PostDto;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.mock.HttpMethod;
import feign.mock.MockClient;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PostClientTest {

    private static String URL_POST = "https://jsonplaceholder.typicode.com";
    private static String RESPONSE_POST = "[{\n" +
            "        \"userId\": 1,\n" +
            "        \"id\": 1,\n" +
            "        \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n" +
            "        \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n" +
            "    }]";

    private PostClient postClient;

    private void builderFeignClient(MockClient mockClient) {

        postClient = Feign.builder()
                .client(mockClient)
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new SpringMvcContract())
                .target(PostClient.class, URL_POST);

    }

    @Test
    public void carregaListaPosts() {

        this.builderFeignClient(new MockClient().ok(
                HttpMethod.GET,
                URL_POST.concat("/posts"),
                RESPONSE_POST
        ));

        List<PostDto> postDtoList = postClient.getAllPosts();

        assertFalse(postDtoList.isEmpty());
        assertThat(postDtoList.get(0).getUserId(), equalTo(1));

    }
}
