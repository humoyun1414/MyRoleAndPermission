package uz.pdp.myroleandpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.myroleandpermission.dto.RoleDto;
import uz.pdp.myroleandpermission.entity.RoleEntity;
import uz.pdp.myroleandpermission.exception.AlreadyExistsException;
import uz.pdp.myroleandpermission.exception.NotFoundException;
import uz.pdp.myroleandpermission.payload.ApiResponses;
import uz.pdp.myroleandpermission.repository.RoleRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public RoleDto addRole(RoleDto roleDto) {
        RoleEntity roleEntity = roleRepository.findByRoleName(roleDto.getRoleName());
        if (roleEntity != null) {
            throw new AlreadyExistsException("This roleName already exists");
        }
        RoleEntity entity = new RoleEntity();
        entity.setRoleName(roleDto.getRoleName());
        entity.setPermissions(roleDto.getPermissions());
        RoleEntity newRole = roleRepository.save(entity);
        roleDto.setId(newRole.getId());
        return roleDto;
    }

    public List<RoleDto> list() {
        List<RoleDto> dtoList = new LinkedList<>();
        roleRepository.findAll().forEach(roleEntity -> {
            dtoList.add(toDto(roleEntity));
        });
        if (dtoList.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        return dtoList;
    }

    public RoleDto toDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleName());
        dto.setPermissions(entity.getPermissions());
        return dto;
    }

    public RoleDto edit(Integer id, RoleDto roleDto) {
        Optional<RoleEntity> roleEntity = roleRepository.findById(id);
        if (roleEntity.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        RoleEntity entity = roleEntity.get();
        entity.setRoleName(roleDto.getRoleName());
        entity.setPermissions(roleDto.getPermissions());
        RoleEntity save = roleRepository.save(entity);
        roleDto.setId(save.getId());
        return roleDto;
    }

    public ApiResponses delete(long id) {

            Optional<RoleEntity> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.delete(optionalRole.get());
            return new ApiResponses("Role deleted", true);
        }
         throw new NotFoundException("Role not found");
    }
}
