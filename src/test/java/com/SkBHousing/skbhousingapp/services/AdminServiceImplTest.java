package com.SkBHousing.skbhousingapp.services;

import com.SkBHousing.skbhousingapp.data.models.Admin;
import com.SkBHousing.skbhousingapp.data.repositories.AdminRepository;
import com.SkBHousing.skbhousingapp.dtos.requests.RegisterAdminRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminService adminService;

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void adminCanBeRegistered_test() {
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("Eniola");
        registerAdminRequest.setLastName("Samuel");
        registerAdminRequest.setEmail("e4Samuel@gmail.com");
        registerAdminRequest.setPassword("e4SamO");
        adminService.registerNewAdmin(registerAdminRequest);
        List<Admin> foundAdmin = adminService.viewAllRegisteredAdmins();
        assertEquals(1, foundAdmin.size());
    }

    @Test
    void adminsCanBeFoundByEmail_Test(){
        RegisterAdminRequest registerAdminRequest = new RegisterAdminRequest();
        registerAdminRequest.setFirstName("Oluwatoni");
        registerAdminRequest.setLastName("Aboluade");
        registerAdminRequest.setEmail("toniAboluade@gmail.com");
        registerAdminRequest.setPassword("toniHay21");
        adminService.registerNewAdmin(registerAdminRequest);

        RegisterAdminRequest registerAdminRequest1 = new RegisterAdminRequest();
        registerAdminRequest1.setFirstName("tomilola");
        registerAdminRequest1.setLastName("temi");
        registerAdminRequest1.setEmail("tomiTemms@gmail.com");
        registerAdminRequest1.setPassword("tomtemms18");
        adminService.registerNewAdmin(registerAdminRequest1);

        List<Admin> foundAdmins = adminService.viewAllRegisteredAdmins();
        Admin thisParticularAdmin = adminService.findAnAdmin(registerAdminRequest.getEmail());
        assertEquals(2, foundAdmins.size());
        assertEquals(thisParticularAdmin.getEmail(), "toniAboluade@gmail.com");
    }

}