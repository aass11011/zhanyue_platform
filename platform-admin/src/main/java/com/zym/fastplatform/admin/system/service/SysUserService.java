package com.zym.fastplatform.admin.system.service;

import com.zym.fastplatform.admin.framework.service.BaseService;
import com.zym.fastplatform.system.entity.RegisteUser;
import com.zym.fastplatform.system.entity.SysUser;
import com.zym.fastplatform.system.entity.dto.PwdDTO;
import com.zym.fastplatform.system.entity.dto.SysUserDTO;
import com.zym.fastplatform.system.entity.vo.SysUserVO;

public interface SysUserService extends BaseService<SysUser, SysUserVO, SysUserDTO> {
    void login(SysUser user);

    void registe(RegisteUser registeUser);

    void save(SysUserDTO entity);

    void add(SysUserDTO user);

    void updatePwd(PwdDTO pwdDTO);
}
