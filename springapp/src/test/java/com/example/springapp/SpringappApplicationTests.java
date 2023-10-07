package com.example.springapp;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {

	@Autowired
	private  MockMvc mockMvc ;

	@Test
	void test_case1() throws Exception{	

		String st = "{\"id\":101,\"name\": \"Demo\",\"badgeNumber\": 1234,\"department\": \"XYZ\"}";
		 mockMvc.perform(MockMvcRequestBuilders.post("/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(st)
					.accept(MediaType.APPLICATION_JSON))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(jsonPath("$").value(true))
					.andReturn();
	}

	@Test
	void test_case2() throws Exception{	

		 mockMvc.perform(get("/101")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.name").value("Demo"))
						.andReturn();
	}

	

	@Test
	void test_case3() throws Exception{	

		String st ="{\"id\":101,\"name\": \"JohnDoe\",\"badgeNumber\": 1234,\"department\": \"XYZ\"}";
		mockMvc.perform(MockMvcRequestBuilders.put("/101")
					   .contentType(MediaType.APPLICATION_JSON)
					   .content(st)
					  .accept(MediaType.APPLICATION_JSON))
					  .andExpect(MockMvcResultMatchers.status().isOk())
					  .andReturn();
	}

	@Test
	void test_case4() throws Exception{	
		
		 mockMvc.perform(get("/")
		 				.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isArray())
						.andReturn();
	}

	@Test
	void test_case5() throws Exception{	
		mockMvc.perform(MockMvcRequestBuilders.delete("/101")
						.accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk())
						.andReturn();
	}

}
