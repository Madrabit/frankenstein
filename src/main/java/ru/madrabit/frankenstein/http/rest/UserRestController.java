package ru.madrabit.frankenstein.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.madrabit.frankenstein.database.entity.Roles;
import ru.madrabit.frankenstein.dto.PageResponse;
import ru.madrabit.frankenstein.dto.UserCreateEditDto;
import ru.madrabit.frankenstein.dto.UserFilter;
import ru.madrabit.frankenstein.dto.UserReadDTO;
import ru.madrabit.frankenstein.service.CompanyService;
import ru.madrabit.frankenstein.service.UserService;
import ru.madrabit.frankenstein.validation.group.CreateAction;
import ru.madrabit.frankenstein.validation.group.UpdateAction;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;
    private final CompanyService companyService;

    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PageResponse<UserReadDTO> findAll(UserFilter filter, Pageable pageable) {
        Page<UserReadDTO> page = userService.findAll(filter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserReadDTO findById(@PathVariable Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDTO create(@Validated({Deprecated.class, CreateAction.class})
                              @RequestBody UserCreateEditDto user
    ) {
        return userService.create(user);
    }

    @PutMapping("/{id}/")
    public UserReadDTO update(@PathVariable("id") Long id,
                         @Validated({Deprecated.class, UpdateAction.class}) @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!userService.delete(id)) {
            new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
