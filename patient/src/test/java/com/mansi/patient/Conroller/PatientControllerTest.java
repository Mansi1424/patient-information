package com.mansi.patient.Conroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mansi.patient.controller.PatientController;
import com.mansi.patient.entity.Patient;
import com.mansi.patient.service.PatientService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    private static final String END_POINT_PATH ="/patient";


    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientService patientService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetAllPatients() throws Exception {
        mockMvc.perform(get("/patient/get")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    public void testAddNewPatient() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/patient/add")
                .content(asJsonString(new Patient("NewPatientTest", "Patel", "02-20-2001", "female", "World", "900-900-9000")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
