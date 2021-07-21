package com.betterSDA.service;

import com.betterSDA.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepo groupRepo;
}