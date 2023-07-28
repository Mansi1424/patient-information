//package com.mansi.patient.Conroller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mansi.patient.controller.PatientController;
//import com.mansi.patient.entity.Patient;
//import com.mansi.patient.service.PatientService;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(PatientController.class)
//public class PatientControllerTest {
//
//    private static final String END_POINT_PATH ="/patient";
//
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @MockBean
//    private PatientService patientService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//
//
//    @Test
//    void shouldCreateMockMvc() {
//        assertNotNull(mockMvc);
//    }
//}
