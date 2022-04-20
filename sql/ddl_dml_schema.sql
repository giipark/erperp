-- utf8mb4
create user `erp`@`%` identified by 'erp';
create user `erp`@`localhost` identified by 'erp';

create database erp character set=utf8mb4;

grant all privileges on erp.* to `erp`@`%` ;
grant all privileges on erp.* to `erp`@`localhost` ;



-- 회원 테이블
CREATE TABLE `tbl_member` (
  `mid` int unsigned  auto_increment  primary key COMMENT '회원 테이블 아이디',
  `userid` varchar(50) unique COMMENT '회원 아이디',
  `userpw` varchar(150) DEFAULT NULL COMMENT '비밀번호 ',
  `username` varchar(50) DEFAULT NULL  COMMENT '회원 이름',
  `email` varchar(100)  unique COMMENT '회원 이메일',
  `regdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
  `updatedate` timestamp NULL DEFAULT NULL COMMENT '수정일'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '회원 테이블';

INSERT INTO tbl_member(userid , userpw , username, regdate)
 VALUES ('admin', '$2a$10$jZsDxTy9IIm0ZI5aoqrole11RaOJ3nGeNZ5.AM5IVRwa1ISS0MvyS', '관리자', now() );

 
-- 자제 테이블 
CREATE TABLE `tbl_material` (
  `maid` int unsigned  auto_increment  primary key  COMMENT '자제 아이디',
  `mcode` varchar(100) unique NOT NULL COMMENT '자재코드',
  `material_name` varchar(100) DEFAULT NULL COMMENT '자재명',
  `proper_inventory` int DEFAULT '0' COMMENT '적정 재고량'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '자재 테이블';
 
 

-- BOM 제품 테이블

CREATE TABLE `tbl_bom_product` (
  `bomid` int unsigned  auto_increment  primary key COMMENT 'BOM 제품 아이디',
  `pcode` varchar(100) unique NOT NULL COMMENT 'BOM 제품 코드',
  `product_name` varchar(100) DEFAULT NULL COMMENT 'BOM 제품 명',
  `product_quantity` int  DEFAULT '1' COMMENT 'BOM 제품 수량'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'BOM 제품 테이블';
 

-- BOM 자제 테이블
CREATE TABLE `tbl_bom_material` (
  `bomid` int unsigned  NOT NULL   COMMENT 'BOM  제품 아이디',
  `maid` int unsigned   NOT NULL COMMENT '자제 아이디',
  `material_name` varchar(100) DEFAULT NULL COMMENT 'BOM 자재명',
  `material_quantity` int  DEFAULT '0' COMMENT 'BOM 자재 수량'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT 'BOM 자재 테이블';
	

-- 생산 테이블
CREATE TABLE `tbl_plan_product` (
  `plan_id` int unsigned  auto_increment  primary key COMMENT '생산 아이디',
  `bomid` int unsigned NOT NULL COMMENT 'BOM 제품 아이디',
  `plan_product_name`  varchar(100) DEFAULT NULL COMMENT '생산 제품 명',
  `plan_product_quantity`  int  DEFAULT '0' COMMENT '생산 개수',
  `deadline` date COMMENT '생산 기한', 
  `remarks` varchar(1000) DEFAULT NULL COMMENT '생산 비고'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '생산 테이블';




-- 입고신청 테이블
CREATE TABLE `tbl_warehosing_materials_apply` (
  `application_code` int unsigned  auto_increment  primary key COMMENT '입고 신청 코드',
  `maid` int unsigned  NOT NULL COMMENT '자재 아이디',
  `warehosing_material_name`  varchar(100) DEFAULT NULL COMMENT '입고 자재명',
  `application_quantity`  int  DEFAULT '0' COMMENT '입고 신청수량',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '입고 비고',
  `process_status` char(1) DEFAULT '0' COMMENT '처리 상태 0:기본,1:입고성공,2:실패입고량초과,3:불출처리'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '입고 신청 테이블';

	

-- 자재정보 테이블
CREATE TABLE `tbl_material_information` (
  `maid` int unsigned primary key NOT NULL COMMENT '자재 아이디',
  `current_inventory` int DEFAULT '0' COMMENT '현재 재고량'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '자재정보 테이블';

	




-- 불출 신청 테이블
CREATE TABLE `tbl_bulchul` (
  `bulchul_id` int  unsigned auto_increment primary key  COMMENT '불출 신청테이블 아이디',
  `plan_id` int unsigned   COMMENT '생산 아이디',
  `maid` int  unsigned    COMMENT '자제 아이디',
  `bomid` int unsigned  COMMENT 'BOM 제품 아이디',
  `application_quantity`  int unsigned  DEFAULT '0' COMMENT '불출 신청수량',
  `remarks` varchar(1000) DEFAULT NULL COMMENT '불출 신청 비고',
  `process_status` char(1) DEFAULT '0' COMMENT '처리 상태 0:일반불출처리,1:생산계획불출처리',
  `application_date` date COMMENT '신청 날짜'  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '불출 신청 테이블';

	


-- 자재정보 테이블
ALTER TABLE tbl_material_information ADD CONSTRAINT
	`tbl_material_information_FK` FOREIGN KEY (`maid`) REFERENCES `tbl_material` (`maid`);


-- 입고신청  테이블
ALTER TABLE tbl_warehosing_materials_apply ADD CONSTRAINT 
	`tbl_warehosing_materials_apply_FK` FOREIGN KEY (`maid`) REFERENCES `tbl_material` (`maid`);



-- BOM 자재  테이블
ALTER TABLE tbl_bom_material ADD CONSTRAINT 
	`tbl_bom_material_FK` FOREIGN KEY (`maid`) REFERENCES `tbl_material` (`maid`);

	
	
-- 불출 신청 테이블'
ALTER TABLE tbl_bulchul ADD CONSTRAINT 
	`tbl_bulchul_FK` FOREIGN KEY (`maid`) REFERENCES `tbl_material` (`maid`);

---
---  Bomid  외래키 설정
---
-- 생산 테이블 테이블'
ALTER TABLE tbl_plan_product ADD CONSTRAINT 
	`tbl_plan_product_FK` FOREIGN KEY (`bomid`) REFERENCES `tbl_bom_product` (`bomid`);	
	
	
-- 'BOM 자재 테이블
ALTER TABLE tbl_bom_material ADD CONSTRAINT 
	`tbl_bom_material_bom_FK` FOREIGN KEY (`bomid`) REFERENCES `tbl_bom_product` (`bomid`);	
	
	
	
-- '불출 신청 테이블'
ALTER TABLE tbl_bulchul ADD CONSTRAINT 
	`tbl_bulchul_bom_FK` FOREIGN KEY (`bomid`) REFERENCES `tbl_bom_product` (`bomid`);	
	

--	
-- 생산 아이디
-- 	
-- 	ALTER TABLE tbl_bulchul ADD CONSTRAINT 
-- 		`tbl_bulchul_planid_FK` FOREIGN KEY (`plan_id`) REFERENCES `tbl_plan_product` (`plan_id`);	
	
	
	
