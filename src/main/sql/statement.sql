# 查询用户拥有的角色
SELECT
  su.user_name,
  role.role_name
FROM
  sys_user su
  LEFT JOIN
  user_role ur ON su.id = ur.user_id
  LEFT JOIN
  sys_role role ON ur.role_id = role.id
WHERE
  su.user_name = 'dba';

# 查询访问资源需要的角色权限

SELECT
  res.res_url,
  role.role_name,
  rr.id
FROM
  sys_resource res
  LEFT JOIN
  res_role rr ON res.id = rr.res_id
  LEFT JOIN
  sys_role role ON rr.role_id = role.id;
    