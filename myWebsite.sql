/*
Navicat MySQL Data Transfer

Source Server         : 120.78.209.159
Source Server Version : 50719
Source Host           : 120.78.209.159:3306
Source Database       : myWebsite

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-01-17 22:10:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for center_tb
-- ----------------------------
DROP TABLE IF EXISTS `center_tb`;
CREATE TABLE `center_tb` (
  `id` varchar(32) NOT NULL,
  `mark` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment_tb
-- ----------------------------
DROP TABLE IF EXISTS `comment_tb`;
CREATE TABLE `comment_tb` (
  `id` varchar(32) NOT NULL,
  `noteId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `content` text NOT NULL,
  `textContent` text NOT NULL,
  `agree` int(11) NOT NULL,
  `date` varchar(200) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for leaveMessage_tb
-- ----------------------------
DROP TABLE IF EXISTS `leaveMessage_tb`;
CREATE TABLE `leaveMessage_tb` (
  `id` varchar(32) NOT NULL,
  `content` text NOT NULL,
  `userid` varchar(32) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for note_resource_tb
-- ----------------------------
DROP TABLE IF EXISTS `note_resource_tb`;
CREATE TABLE `note_resource_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `noteId` varchar(32) NOT NULL,
  `resourceId` varchar(32) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for note_tb
-- ----------------------------
DROP TABLE IF EXISTS `note_tb`;
CREATE TABLE `note_tb` (
  `id` varchar(32) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(100) NOT NULL,
  `content` text NOT NULL,
  `classifyid` varchar(32) DEFAULT NULL COMMENT '分类id',
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for noteclassify_tb
-- ----------------------------
DROP TABLE IF EXISTS `noteclassify_tb`;
CREATE TABLE `noteclassify_tb` (
  `id` varchar(32) NOT NULL COMMENT '分类唯一id',
  `classifyName` varchar(255) NOT NULL COMMENT '分类名字',
  `del_flag` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for parent_permission_relation_tb
-- ----------------------------
DROP TABLE IF EXISTS `parent_permission_relation_tb`;
CREATE TABLE `parent_permission_relation_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentid` varchar(32) NOT NULL,
  `permissionid` varchar(32) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for parent_permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `parent_permission_tb`;
CREATE TABLE `parent_permission_tb` (
  `id` varchar(32) NOT NULL,
  `parent` varchar(255) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for parent_url_tb
-- ----------------------------
DROP TABLE IF EXISTS `parent_url_tb`;
CREATE TABLE `parent_url_tb` (
  `id` varchar(32) NOT NULL,
  `url` varchar(255) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `isAuthority` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `permission_tb`;
CREATE TABLE `permission_tb` (
  `id` varchar(32) NOT NULL,
  `permission` varchar(100) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for picture_tb
-- ----------------------------
DROP TABLE IF EXISTS `picture_tb`;
CREATE TABLE `picture_tb` (
  `id` varchar(32) NOT NULL,
  `location` varchar(1000) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `del_flag` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for reply_tb
-- ----------------------------
DROP TABLE IF EXISTS `reply_tb`;
CREATE TABLE `reply_tb` (
  `id` varchar(32) NOT NULL,
  `commentId` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `sequence` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `replyTo` varchar(255) NOT NULL COMMENT '回复@张三:',
  `date` varchar(200) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for resource_tb
-- ----------------------------
DROP TABLE IF EXISTS `resource_tb`;
CREATE TABLE `resource_tb` (
  `id` varchar(32) NOT NULL,
  `resourceName` varchar(255) NOT NULL,
  `resourceType` varchar(10) NOT NULL,
  `vitrualPath` varchar(255) NOT NULL,
  `absolutePath` varchar(255) NOT NULL,
  `md5` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date` varchar(200) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_permission_tb
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_tb`;
CREATE TABLE `role_permission_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` varchar(32) NOT NULL,
  `permissionid` varchar(32) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `角色id2` (`roleid`),
  KEY `权限id` (`permissionid`),
  CONSTRAINT `权限id` FOREIGN KEY (`permissionid`) REFERENCES `permission_tb` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `角色id2` FOREIGN KEY (`roleid`) REFERENCES `role_tb` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_tb
-- ----------------------------
DROP TABLE IF EXISTS `role_tb`;
CREATE TABLE `role_tb` (
  `id` varchar(32) NOT NULL,
  `role` varchar(100) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for skill_p_tb
-- ----------------------------
DROP TABLE IF EXISTS `skill_p_tb`;
CREATE TABLE `skill_p_tb` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `mark` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for skill_relation_tb
-- ----------------------------
DROP TABLE IF EXISTS `skill_relation_tb`;
CREATE TABLE `skill_relation_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(32) NOT NULL,
  `sid` varchar(32) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for skill_s_tb
-- ----------------------------
DROP TABLE IF EXISTS `skill_s_tb`;
CREATE TABLE `skill_s_tb` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `mark` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for trace_tb
-- ----------------------------
DROP TABLE IF EXISTS `trace_tb`;
CREATE TABLE `trace_tb` (
  `id` varchar(32) NOT NULL,
  `content` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `mark` int(11) NOT NULL,
  `information` text,
  `newdate` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for url_tb
-- ----------------------------
DROP TABLE IF EXISTS `url_tb`;
CREATE TABLE `url_tb` (
  `id` varchar(32) NOT NULL,
  `url` varchar(255) NOT NULL,
  `describe` varchar(255) DEFAULT NULL,
  `methodName` varchar(255) NOT NULL,
  `className` varchar(255) NOT NULL,
  `isAuthority` int(11) NOT NULL,
  `parentMapping` varchar(255) NOT NULL,
  `sonMapping` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_account_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_account_tb`;
CREATE TABLE `user_account_tb` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_infor_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_infor_tb`;
CREATE TABLE `user_infor_tb` (
  `id` varchar(32) NOT NULL,
  `nickname` varchar(32) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `photo` varchar(32) DEFAULT NULL,
  `date` varchar(255) NOT NULL,
  `newdate` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `头像id` (`photo`),
  CONSTRAINT `头像id` FOREIGN KEY (`photo`) REFERENCES `picture_tb` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_role_tb`;
CREATE TABLE `user_role_tb` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(32) NOT NULL,
  `roleid` varchar(32) NOT NULL,
  `date` varchar(255) NOT NULL,
  `del_flag` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `角色id` (`roleid`),
  KEY `用户id` (`userid`),
  CONSTRAINT `用户id` FOREIGN KEY (`userid`) REFERENCES `user_account_tb` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `角色id` FOREIGN KEY (`roleid`) REFERENCES `role_tb` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6157 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Procedure structure for bindRole
-- ----------------------------
DROP PROCEDURE IF EXISTS `bindRole`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `bindRole`(In p_userid VARCHAR(32),IN p_roleid VARCHAR(32),IN p_del_flag int)
BEGIN
	declare count int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from user_role_tb where userid=p_userid and roleid = p_roleid);
	start TRANSACTION;
		if count >0 THEN
		-- 存在
			update user_role_tb set del_flag = p_del_flag where userid = p_userid and roleid = p_roleid;
		ELSE
			insert into user_role_tb VALUES(null,p_userid,p_roleid,Date_format(Now(),'%Y-%m-%d %T'),p_del_flag);
		end IF;
		
		-- 提交事务
		
		IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;
		
	
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteComment
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteComment`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `deleteComment`(In p_id VARCHAR(32))
BEGIN
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	update comment_tb set del_flag=1 where id=p_id; 
	
	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for deleteUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `deleteUser`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `deleteUser`(IN p_id varchar(32))
begin 
		DECLARE accountNumber INT;
		DECLARE error_code INT;
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
		Set accountNumber = (select count(*) from user_account_tb where id = p_id and del_flag = 0);
		start TRANSACTION;
			if accountNumber >0 then 
				-- 存在，需要删除，而且是级联删除
				update user_role_tb set del_flag=1 where userid = p_id;
				update user_account_tb set del_flag = 1 where id = p_id;
			end IF;
				-- 提交事务
			IF error_code = 1 THEN
					ROLLBACK;
					select "error";
			ELSE
					COMMIT;
					select "succ";
			END IF;
			

end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for initializeAuthority
-- ----------------------------
DROP PROCEDURE IF EXISTS `initializeAuthority`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `initializeAuthority`()
begin
	-- 需要初始化‘普通用户’ ‘管理员’ ‘超级管理员’ 这三个角色
	-- 首先查找是否创建了普通用户的角色.
		DECLARE	userCount int;
		DECLARE	adminCount int;
		DECLARE	superAdminCount int;
		DECLARE id varchar(32);
		DECLARE error_code INT;
		DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
		
		
		
		set userCount = (select count(*) from role_tb where role='user' and del_flag = 0); 
		
		set adminCount = (select count(*) from role_tb where role='admin' and del_flag = 0);
		
		set superAdminCount = (select count(*) from role_tb where role='superAdmin' and del_flag = 0);
		start transaction	;
				if userCount < 1 
					then 
						-- 不存在则插入						
							set id = (SELECT REPLACE(UUID(), '-', ''));
							insert into role_tb values(id,'user','普通用户',Date_format(Now(),'%Y-%m-%d %T'),Date_format(Now(),'%Y-%m-%d %T'),0);
						
				end if;
						
				if adminCount < 1 
					then 
						-- 不存在则插入						
							set id = (SELECT REPLACE(UUID(), '-', ''));
							insert into role_tb values(id,'admin','管理员',Date_format(Now(),'%Y-%m-%d %T'),Date_format(Now(),'%Y-%m-%d %T'),0);
						
				end if	;		
				if superAdminCount < 1 
					then 
						-- 不存在则插入				
							set id = (SELECT REPLACE(UUID(), '-', ''));
							insert into role_tb values(id,'superAdmin','超级管理员',Date_format(Now(),'%Y-%m-%d %T'),Date_format(Now(),'%Y-%m-%d %T'),0);
						
				end if;
				-- 提交事务
		
				IF error_code = 1 THEN
						ROLLBACK;
						select "error";
				ELSE
						COMMIT;
										select "succ";
				END IF;
	
    
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insertPermissionToRole
-- ----------------------------
DROP PROCEDURE IF EXISTS `insertPermissionToRole`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `insertPermissionToRole`(IN p_roleid VARCHAR(32),IN p_permissionid VARCHAR(32))
BEGIN
	declare count int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from role_permission_tb where roleid = p_roleid and permissionid = p_permissionid);
	if count > 0 THEN
			-- 已经存在了，那就更新
			update role_permission_tb set del_flag=0 where roleid=p_roleid and permissionid = p_permissionid;
	ELSE
			-- 不存在就添加
			insert into role_permission_tb values(NULL,p_roleid,p_permissionid,Date_format(Now(),'%Y-%m-%d %T'),0);
	END IF;
	
	
	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for removeResourceById
-- ----------------------------
DROP PROCEDURE IF EXISTS `removeResourceById`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `removeResourceById`(In p_id VARCHAR(32),In p_noteId VARCHAR(32))
BEGIN
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	update note_resource_tb set del_flag=1 where noteId=p_noteId and resourceId = p_id;
	update resource_tb set del_flag = 1 where id = p_id;
	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveComment
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveComment`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveComment`(In p_id VARCHAR(32),IN p_noteId VARCHAR(32),IN p_userId VARCHAR(32),IN p_content text,IN p_textContent text,IN p_agree int,IN p_date varchar(200),IN p_del_flag int)
BEGIN
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	insert into comment_tb VALUES(p_id,p_noteId,p_userId,p_content,p_textContent,p_agree,p_date,p_del_flag);
	
	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveMapping
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveMapping`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveMapping`(IN p_id VARCHAR(32),IN p_date VARCHAR(255),IN p_del_flag INT,IN p_url VARCHAR(255),IN p_className VARCHAR(255),IN p_methodName VARCHAR(255),IN p_isAuthority INT,IN p_describe VARCHAR(32),IN p_parentMapping VARCHAR(32),IN p_sonMapping VARCHAR(32))
BEGIN
	declare count int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from url_tb where url = p_url);
	start TRANSACTION;
		if count>0 THEN
				-- 找到了，那就更新它的del_flag=0，防止它被删除了
				update url_tb set del_flag = p_del_flag where url = p_url;
		ELSE 
				-- 没找到，那就插入
				insert into url_tb values(p_id,p_url,p_describe,p_methodName,p_className,p_isAuthority,p_parentMapping,p_sonMapping,p_date,p_del_flag);
		end IF;
		-- 提交事务
		
		IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
                select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveNote
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveNote`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveNote`(IN p_id VARCHAR(32),IN p_title VARCHAR(255),IN p_author VARCHAR(255) ,IN p_content text,IN p_date varchar(255),IN p_newdate varchar(255),IN p_del_flag INT,IN p_classifyid varchar(32),IN p_classifyName VARCHAR(255)  )
BEGIN
	DECLARE var int ;
	DECLARE classify_var INT;
	DECLARE error_code INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; -- 在执行过程中出任何异常设置result_code为1
	SET var = (select count(*) from note_tb where id = p_id and del_flag = 0);
	SET classify_var = (select count(*) from noteclassify_tb WHERE id = p_classifyid and del_flag = 0);
	
	
	-- 开始事物
	START TRANSACTION;
		 -- select classify_var ;
		if classify_var < 1 THEN
			-- 如果classify_tb中没有分类数据,则执行插入，表示这个分类是不存在的，如果存在则不用管
			INSERT INTO noteclassify_tb VALUES(p_classifyid,p_classifyName,p_del_flag,p_date);
             -- select "insert class";
		END IF;
		 -- select var ; 
		if var > 0 THEN
			-- 如果note表中有数据，则执行更新
			update note_tb
			SET	
					title = p_title,content = p_content,author = p_author,newdate = p_newdate,del_flag = p_del_flag,classifyid = p_classifyid
			where id = p_id and del_flag = 0;
			 -- select "update note";
		ELSE
			insert into note_tb values(p_id,p_title,p_author,p_content,p_classifyid,p_date,p_newdate,p_del_flag);
             -- select "insert note";
		end IF;

		-- 提交事务
		
		IF error_code = 1 THEN
				ROLLBACK;
				 -- select "error";
		ELSE
				COMMIT;
                -- select "succ";
		END IF;
	
	select * from note_tb where id = p_id and del_flag = 0;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveParentMapping
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveParentMapping`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveParentMapping`(IN p_id VARCHAR(32),IN p_date VARCHAR(255),IN p_del_flag INT,IN p_url VARCHAR(255),IN p_isAuthority INT,IN p_describe VARCHAR(32))
BEGIN
	declare count int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from parent_url_tb where url = p_url);
	start TRANSACTION;
		if count>0 THEN
				-- 找到了，那就更新它的del_flag=0，防止它被删除了
				update parent_url_tb set `describe` = p_describe,isAuthority=p_isAuthority,del_flag=p_del_flag where url = p_url;
		ELSE 
				-- 没找到，那就插入
				insert into parent_url_tb values(p_id,p_url,p_describe,p_isAuthority,p_date,p_del_flag);
		end IF;
		-- 提交事务
		
		IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for savePermission
-- ----------------------------
DROP PROCEDURE IF EXISTS `savePermission`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `savePermission`(IN p_id VARCHAR(32),IN p_permission VARCHAR(100),IN p_describe VARCHAR(32),IN p_date VARCHAR(255),IN p_newdate varchar(255),IN p_del_flag INT,IN p_parentid varchar(32),IN p_parent varchar(32))
BEGIN
	declare count int;
	declare pcount int;
	DECLARE rcount int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from permission_tb where id = p_id or permission = p_permission);
	set pcount = (select count(*) from parent_permission_tb where id = p_parentid or parent = p_parent);
	set rcount = (select count(*) from parent_permission_relation_tb where  permissionid = p_id);
	start TRANSACTION;
		-- 首先先看权限表有没有数据，没有数据就插入数据,有数据就更新数据
		if count >0 THEN
			UPDATE permission_tb set permission=p_permission ,del_flag = p_del_flag,newdate = p_newdate,`describe`=p_describe where id = p_id;
		ELSE
			insert into permission_tb values(p_id,p_permission,p_describe,p_date,p_newdate,p_del_flag);
		end if;


		-- 再看看有没有父节点的数据，没有就插入
/*
这里为什么不更新父节点数据？因为子节点增删不应该影响到父节点，即子节点被删除，父节点应该还在，但是关系就不一样了，子节点被删除，关系应当也删除
所以这里只不更新父节点的数据，其他表都要更新
*/
		if pcount <1 THEN
			
			insert into parent_permission_tb VALUES(p_parentid,p_parent,null,p_date,p_del_flag);

		end IF;


		-- 再看看有没有关系数据
		if rcount > 0 THEN 

			update parent_permission_relation_tb set del_flag = p_del_flag where  permissionid = p_id;

		ELSE
-- 保险插入 
			insert into parent_permission_relation_tb VALUES(null,(select id from parent_permission_tb where parent = p_parent),p_id,p_date,p_del_flag);

		end if;



/*
		if p_isAuthority = 0 THEN 
					update url_tb set isAuthority = p_isAuthority ,del_flag = p_del_flag where id = p_id;
					if count>0 THEN
				-- 找到了，那就更新它的del_flag=0，防止它被删除了
				update permission_tb set `describe` = p_describe,permission=p_permission,newdate=p_newdate,del_flag=p_del_flag where id = p_id;
					ELSE 
							-- 没找到，那就插入
							insert into permission_tb values(p_id,p_permission,p_describe,p_date,p_newdate,p_del_flag);
					end IF;
					-- 提交事务

		ELSE  
					-- 进行urlmapping的更新
				update url_tb set isAuthority = p_isAuthority ,del_flag = p_del_flag where id = p_id;

				if count>0 THEN
						-- 如果找到了，那就直接更新，没找到的话就插入，不过del_flag=1
				-- 更新permission表
						update permission_tb set del_flag = p_isAuthority where id = p_id;
				else
			-- 没找到的话就插入，不过del_flag=1 
						insert into permission_tb values(p_id,p_permission,p_describe,p_date,p_newdate,isAuthority);
				end if;
		end IF;
*/
		
		IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveReply
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveReply`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveReply`(In p_id VARCHAR(32),IN p_commentId VARCHAR(32),IN p_userId VARCHAR(32),IN p_sequence int,IN p_content text,IN p_replyTo varchar(255) ,IN p_date varchar(200),IN p_del_flag int)
BEGIN
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	insert into reply_tb VALUES(p_id,p_commentId,p_userId,p_sequence,p_content,p_replyTo,p_date,p_del_flag);
	
	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveResourceByNoteId
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveResourceByNoteId`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveResourceByNoteId`(In p_id VARCHAR(32),IN p_resourceName VARCHAR(255),IN p_resourceType VARCHAR(10),IN p_vitrualPath VARCHAR(255),IN p_absolutePath VARCHAR(255),IN p_md5 varchar(32) ,IN p_description VARCHAR(255),IN p_date varchar(200),IN p_del_flag int,IN p_noteId varchar(32))
BEGIN
	DECLARE error_code INT;
	DECLARE resourceCount INT;
	DECLARE resourceRealCount INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	/**
	这是给笔记保存/更新资源的单个方法，在此方法前，该笔记的所有资源关联已经被删除，所以该方法的主要任务是
	1、查找笔记是否有该资源Id，如果有的话，就update它的del为0
	2、
**/
	SET resourceCount = (select count(*) from note_resource_tb where noteId = p_noteId and resourceId = p_id);
	SET resourceRealCount = (select count(*) from resource_tb where id = p_id );
	IF resourceCount = 0 THEN
		##插入
		insert into note_resource_tb VALUES(null,p_noteId,p_id,p_del_flag);
		IF resourceRealCount = 0 THEN
			##插入
			insert into resource_tb  VALUES(p_id,p_resourceName,p_resourceType,p_vitrualPath,p_absolutePath,p_md5,p_description,p_date,p_del_flag);
		ELSE 
			##更新
			UPDATE resource_tb set del_flag = p_del_flag where id=p_id;
		END IF;
		
 ELSE
		update note_resource_tb set del_flag=0;
		IF resourceRealCount = 0 THEN
			##插入
			insert into resource_tb  VALUES(p_id,p_resourceName,p_resourceType,p_vitrualPath,p_absolutePath,p_md5,p_description,p_date,p_del_flag);
		ELSE 
			##更新
			UPDATE resource_tb set del_flag = p_del_flag where id=p_id;
		END IF;
	END IF;

	IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveRole
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveRole`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveRole`(In p_id VARCHAR(32),IN p_role VARCHAR(100),IN p_describe VARCHAR(255),IN p_date VARCHAR(255),IN p_newdate VARCHAR(255),IN p_del_flag int)
BEGIN
	declare count int;
	declare c int;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	set count = (select count(*) from role_tb where id = p_id or role = p_role);
	set c =(select count(*) from user_role_tb where roleid=p_id and del_flag=0);
	if count > 0 && p_del_flag=0 THEN
			-- 已经存在了，那就更新
			-- select "存在";
			update role_tb set newdate = p_newdate,`describe`=p_describe,del_flag=p_del_flag where id=p_id;
	ELSEIF count = 0 && p_del_flag=0 THEN
			-- 不存在就添加
			 insert into role_tb values(p_id,p_role,p_describe,p_date,p_newdate,p_del_flag);
	END IF;
	
	IF p_del_flag = 1 THEN
			-- 删除
				update role_permission_tb set del_flag = p_del_flag where roleid = p_id;
			-- 删除用户角色

				if c > 0 THEN
					 set error_code=2;
					-- select "存在用户拥有该角色！";
				else 
					update role_tb set del_flag=p_del_flag where id=p_id;
				end if;
	END IF;

		IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSEIF error_code=2 then
				ROLLBACK;
        select "存在用户拥有该角色！";
		else 
				COMMIT;
				select "succ";
		END IF;

END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveSkillP
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveSkillP`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveSkillP`(in p_id varchar(32),in p_name varchar(255),in p_description varchar(500),in p_mark int,in p_date varchar(255),in p_newdate varchar(255),in p_del int)
begin
	declare var int;
    DECLARE error_code INT;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; -- 在执行过程中出任何异常设置result_code为1
    set var = (select count(*) from skill_p_tb where id=p_id and del_flag=0);
    
    start transaction;
		if var >0 then
        -- 说明已经又这个skillP了，那就更新
			update skill_p_tb
            set
				name=p_name,description=p_description,mark = p_mark,date = p_date,newdate=p_newdate,del_flag=p_del
			where
                id = p_id and del_flag = 0;
        else
		-- 说明没有这个skillP，那就插入
			insert into skill_p_tb values(p_id,p_name,p_description,p_mark,p_date,p_newdate,p_del);
        end if;
        
        -- 提交事务
		
		IF error_code = 1 THEN
				ROLLBACK;
				 select 1;
		ELSE
				COMMIT;
                select 0;
		END IF;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveSkillSon
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveSkillSon`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveSkillSon`(in p_id varchar(32),in p_name varchar(255),in p_description varchar(500),in p_mark int,in p_num int,in p_date varchar(255),in p_newdate varchar(255),in p_del int,in p_pid varchar(32))
begin
	declare var int;
    declare var2 int;
    DECLARE error_code INT;
     DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; -- 在执行过程中出任何异常设置result_code为1
    set var = (select count(*) from skill_s_tb where id=p_id and del_flag=0);
    set var2 = (select count(*) from skill_relation_tb where sid = p_id and pid = p_pid and del_flag=0);
    -- select error_code '0';
    start transaction;
		if var >0 then
        -- 说明已经又这个skillS了，那就更新
			update skill_s_tb
            set
				name=p_name,description=p_description,mark = p_mark,num = p_num,date = p_date,newdate=p_newdate,del_flag=p_del
			where
                id = p_id and del_flag = 0;
			
           --  select error_code '1';
        else
		-- 说明没有这个skills，那就插入
			insert into skill_s_tb values(p_id,p_name,p_description,p_mark,p_num,p_date,p_newdate,p_del);
            -- select error_code '2';
        end if;
        
        if var2<1 then
        -- 没有这个关系，那么就插入
			insert into skill_relation_tb values(null,p_pid,p_id,p_del);
            -- select error_code '3';
        end if;
        
        -- 提交事务
		
		 IF error_code = 1 THEN
		 		ROLLBACK;
		 		 select 1;
		 ELSE
				COMMIT;
                select 0;
		 END IF;
end
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveTacModel
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveTacModel`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveTacModel`(IN p_id VARCHAR(32),IN p_mark INT,IN p_title VARCHAR
(255),IN p_content VARCHAR(1000),IN p_date varchar(255),IN p_del_flag INT)
BEGIN 
	DECLARE var INT;
	#查询是否存在此条id记录，用以判断是插入还是更新
	set var = (select count(*) from center_tb where id = p_id and del_flag =0 );
	if var >0 then 
	#存在此条记录,则更新内容
			UPDATE center_tb 
			SET
					title = p_title,
					content = p_content,
					date = p_date
			WHERE id = p_id and del_flag = 0;
	ELSE
			insert into center_tb VALUES		
			(p_id,p_mark,p_title,p_content,p_date,p_del_flag);
	END IF;
			
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for saveUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `saveUser`;
DELIMITER ;;
CREATE DEFINER=`myWebsite`@`%` PROCEDURE `saveUser`(IN p_id VARCHAR(32),IN p_username VARCHAR(32),IN p_password VARCHAR(255),IN p_salt VARCHAR(255),IN p_date VARCHAR(255),IN p_newdate VARCHAR(255),IN p_del_flag INT)
BEGIN
	DECLARE accountNumber INT;
	DECLARE error_code INT;
  DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET error_code=1; 
	Set accountNumber = (select count(*) from user_account_tb where id = p_id and del_flag = 0);
	  start TRANSACTION ;
		if accountNumber<1 THEN
			-- 不存在则插入
			-- 注意这里要插入很多表
					INSERT into user_account_tb values(p_id,p_username,p_password,p_salt,p_date,p_newdate,p_del_flag); -- 插入用户账户
					INSERT into user_role_tb values (null,p_id,(select id from role_tb where role = 'user' and del_flag = 0),p_date,0); -- 插入角色信息
					INSERT into user_infor_tb values (p_id,p_username,null,null,null,null,p_date,p_newdate,p_del_flag);-- 初始化用户信息
				
		ELSE 
			-- 存在则更新
			-- 这里只更新用户账户，角色和权限等不在这里更新
					update user_account_tb a
					set  `password`=p_password ,newdate = p_newdate,del_flag = p_del_flag 
					where id = p_id and del_flag = 0;
		END IF;

		-- 提交事务
		 IF error_code = 1 THEN
				ROLLBACK;
				select "error";
		ELSE
				COMMIT;
        select "succ";
		END IF;
END
;;
DELIMITER ;
SET FOREIGN_KEY_CHECKS=1;
