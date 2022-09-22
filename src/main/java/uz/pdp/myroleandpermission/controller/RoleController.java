package uz.pdp.myroleandpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.myroleandpermission.dto.RoleDto;
import uz.pdp.myroleandpermission.service.RoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('ADD_ROLE')")
    @PostMapping("/adm")
    public HttpEntity<?> addRole(@RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.addRole(roleDto));
    }

    @PreAuthorize("hasAuthority('VIEW_ROLE')")
    @GetMapping("/adm")
    public HttpEntity<?> getRole() {
        return ResponseEntity.ok(roleService.list());
    }

    @PutMapping("/adm/edit")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody RoleDto roleDto) {
        return ResponseEntity.ok(roleService.edit(id, roleDto));
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/adm/{id}")
    public HttpEntity<?> delete(@PathVariable long id) {
        return ResponseEntity.ok(roleService.delete(id));
    }

}
