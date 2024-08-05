package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return new ArrayList<>();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGroup(GroupDto groupDto) {

    }

    @GetMapping(value = "{groupId}")
    public GroupDto getGroup(Long groupId) {
        return new GroupDto(1L, "testGroup");
    }

    @PutMapping(value = "{groupId}")
    public GroupDto updateGroup(GroupDto groupDto) {
        return new GroupDto(1L, "testGroup");
    }
}
