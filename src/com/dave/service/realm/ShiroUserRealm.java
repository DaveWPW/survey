package com.dave.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dave.dao.MenuDao;
import com.dave.dao.UserDao;
import com.dave.entity.User;

/**
 * Realm实现类
 * 
 * @author Dave20191010
 *
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm {
	private static Logger logger = Logger.getLogger(ShiroUserRealm.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private MenuDao menuDao;
	
	private Map<String, SimpleAuthorizationInfo> cacheMap = new ConcurrentHashMap<>();
	
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		cMatcher.setHashAlgorithmName("MD5");
		super.setCredentialsMatcher(cMatcher);
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String username = (String)token.getPrincipal();
		User user = userDao.findUserByUserName(username);
		if(user == null) {
			logger.info("用户不存在");
			throw new UnknownAccountException();
		}
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getPasswordSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, user.getPassword(), credentialsSalt, getName());
		if(cacheMap.containsKey(username)) {
			cacheMap.remove(username);
		}
		return info;
	}
    
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User)principals.getPrimaryPrincipal();
		if(cacheMap.containsKey(user.getUsername())) {
			return cacheMap.get(user.getUsername());
		}
		List<String> permissions = menuDao.findRoleMenuLevelById(user.getRoleId());
		if(permissions == null || permissions.size() == 0) {
			logger.info("该角色不存在任何菜单权限");
			throw new AuthorizationException();
		}
		Set<String> permissionSet = new HashSet<>();
		for(String permission : permissions){
			if(!StringUtils.isEmpty(permission)){
				permissionSet.add(permission);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permissionSet);
		cacheMap.put(user.getUsername(), info);
		return info;
	}
}