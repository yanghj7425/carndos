package com.carndos.modules.res.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.carndos.modules.commons.service.impl.AbstractBaseService;
import com.carndos.modules.res.service.ResRoleService;
import com.carndos.modules.res.dao.ResRoleMapper;
import com.carndos.modules.res.entity.SysResRole;
import com.carndos.modules.res.pojo.ResRoleBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("resRoleService")
@Transactional(rollbackFor = {Exception.class})
public class ResRoleServiceI extends AbstractBaseService<ResRoleBO, Mapper<ResRoleBO>> implements ResRoleService {

    @Resource
    private ResRoleMapper resRoleMapper;

    @Override
    public List<ResRoleBO> queryAll() {
        return resRoleMapper.queryAllResRole();
    }


    /**
     * @param resId   resource`s id that would be assigned to roles
     * @param roleIds this is a array of role`id
     * @return list  res_role`s primary keys
     * @description this option will be divide two step, at first set res_role`s res_status in 0.The second will core option that assign roles
     */
    @Override
    public List<Integer> saveResToRole(String resId, String[] roleIds) {
        List<Integer> list = CollectionUtil.newArrayList();
        SysResRole sysResRole = new SysResRole();
        sysResRole.setResId(resId);

        updateResStatus("0", resId, roleIds);

        for (String roleId : roleIds) {
            sysResRole.setRoleId(roleId);
            resRoleMapper.saveResToRole(sysResRole);
            list.add(sysResRole.getId());
        }
        return list;
    }

    /**
     * @param resStatus res_role`s res_status
     * @param resId     sys_resource`s Id
     * @param roleIds   role`s id array
     * @return count of updated records
     */
    public Integer updateResStatus(String resStatus, String resId, String[] roleIds) {
        Map<String, Object> resMap = CollectionUtil.newHashMap();
        resMap.put("resStatus", resStatus);
        resMap.put("resId", resId);
        resMap.put("roleIds", roleIds);
        return resRoleMapper.updateResStatus(resMap);
    }


    /**
     * @param resId The resource`s Id
     * @return list
     */
    @Override
    public List<Integer> queryResAssignedRoleIds(String resId) {
        return resRoleMapper.queryResAssignedRoleIds(resId);
    }
}

