
create database locationAnalyticsManager;

CREATE TABLE locationAnalyticsManager.User (
    userId  int not null primary key,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    userName varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    gender varchar(20) NOT NULL,
    age int NOT NULL

);

CREATE TABLE locationAnalyticsManager.BusinessUser (
    businessUserId int not null primary key,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    userName varchar(100) NOT NULL,
    password varchar(100) NOT NULL,
    securityQuestion varchar(100) NOT NULL,
    answer varchar(100) NOT NULL

);

CREATE  TABLE locationAnalyticsManager.UserLikings (
  userLikingId int NOT NULL primary key,
  userLikings VARCHAR(300) NOT NULL ,
  u_Id INT NOT NULL ,
  FOREIGN KEY (u_Id) REFERENCES locationAnalyticsManager.User(userId) 
  );

  CREATE  TABLE locationAnalyticsManager.PromotionalDeals (
   promotionId int not null primary key,
   promotionName  VARCHAR(50) NOT NULL  unique,
   promotionDescription  VARCHAR(200) NOT NULL ,
   promotionType VARCHAR(10) NOT NULL ,
   promotionStartDate DATE,
   promotionEndDate DATE,
   dealStatus boolean,
   promotionLocationAddress varchar(200) NOT NULL,
   promotionLocationLattitude point NOT NULL,
   promotionLocationLongitude point NOT NULL,
   promotionLocationAltitude point NOT NULL

   );

  CREATE  TABLE locationAnalyticsManager.PromotionAdDetails (
   adId int not null primary key,
   adName VARCHAR(50) NOT NULL  unique,
   adDescription VARCHAR(200) NOT NULL ,
   adDetail VARCHAR(200) NOT NULL ,
   adTags VARCHAR(200) NOT NULL ,
   adCount INT ,
   dealSubcriptionCount int not null,
   numberOfAttendees INT ,
   adScheduleDetail VARCHAR(200) ,
   p_Id INT NOT NULL ,
   FOREIGN KEY (p_Id) REFERENCES locationAnalyticsManager.PromotionalDeals(promotionId) 
   );