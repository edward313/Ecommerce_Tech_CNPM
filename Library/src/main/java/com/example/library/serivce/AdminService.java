package com.example.library.serivce;

import com.example.library.dto.AdminDto;
import com.example.library.model.Admin;

public interface AdminService {
    Admin findByUsername(String username);

    Admin save(AdminDto adminDto);
}
