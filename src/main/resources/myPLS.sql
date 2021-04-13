-- SWEN 383 myPLS Database

-- DROP DATABASE IF EXISTS heroku_6cdec71a68a6b18;
-- CREATE DATABASE heroku_6cdec71a68a6b18;
USE `heroku_3a5f011235c10c7`;

-- DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userEmail VARCHAR(100) NOT NULL,
  fName VARCHAR(75) NOT NULL,
  lName VARCHAR(75) NOT NULL,
  accountType CHAR (1),
  verificationCode VARCHAR(5),
  verified BOOLEAN NOT NULL,
  userPassword VARCHAR(40) NOT NULL ,
  PRIMARY KEY (userEmail)
) ;

CREATE TABLE class (
classCode VARCHAR(20) NOT NULL,
creatorEmail VARCHAR (100) NOT NULL, -- FK user
professorEmail VARCHAR(100) NOT NULL, -- FK user
className VARCHAR(100) NOT NULL,
learningObj TEXT,
learningOutcome TEXT,
beginDate DATE,
endDate DATE,
PRIMARY KEY (classCode),
-- creatorEmail references the userEmail in user table
CONSTRAINT fk_creatorclass FOREIGN KEY (creatorEmail) REFERENCES user (userEmail) ON DELETE CASCADE,
-- professorEmail references the userEmail in the user table
CONSTRAINT fk_professorclass FOREIGN KEY (professorEmail) REFERENCES user (userEmail) ON DELETE CASCADE
) ;

CREATE TABLE classGrade (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
grade DOUBLE,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassGrade FOREIGN KEY (userEmail) REFERENCES user (userEmail) ON DELETE CASCADE,
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodeclassGrade FOREIGN KEY (classCode) REFERENCES class (classCode) ON DELETE CASCADE
) ;

CREATE TABLE classListLookup (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
lectureID INT NOT NULL,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT fk_userEmailclassListLookup FOREIGN KEY (userEmail) REFERENCES user (userEmail) ON DELETE CASCADE,
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodeclassListLookup FOREIGN KEY (classCode) REFERENCES class (classCode) ON DELETE CASCADE
) ;

CREATE TABLE lesson (
  lessonID INT AUTO_INCREMENT, -- PK
  classCode VARCHAR(20) -- FK class
  lessonName VARCHAR(50),
  startTime DATETIME,
  endTime DATETIME,
  PRIMARY KEY(lessonID),
  CONSTRAINT lessonclassCode FOREIGN KEY (classCode) REFERENCES class(classCode) ON DELETE CASCADE
) ;

CREATE TABLE lectures (
lectureID INT AUTO_INCREMENT,
multimedia TEXT,
lessonID INT, -- FK
PRIMARY KEY (lectureID),
CONSTRAINT lectueslessonID FOREIGN KEY (lessonID) REFERENCES lesson(lessonID) ON DELETE CASCADE
) ;

CREATE TABLE lessonLookup (
classCode VARCHAR(20), -- FK class
lessonID INT, -- FK lectures
PRIMARY KEY (classCode, lessonID),
-- classCodereferences the classCodein the class table
CONSTRAINT fk_classCodelectureLookup FOREIGN KEY (classCode) REFERENCES class (classCode) ON DELETE CASCADE,
-- lectureID references the lectureID in the lectures table
CONSTRAINT fk_lectureIDlectureLookup FOREIGN KEY (lessonID) REFERENCES lesson (lessonID) ON DELETE CASCADE
) ;

CREATE TABLE lessonRating (
userEmail VARCHAR (100), -- FK users
lessonID INT,  -- FK lectures
rating INT,
PRIMARY KEY (userEmail, lectureID),
-- userEmail references the userEmail in the user table
CONSTRAINT userEmaillectureRating FOREIGN KEY (userEmail) REFERENCES user (userEmail) ON DELETE CASCADE,
-- lectureID references the lectureID in the lectures table
CONSTRAINT lessonIDlectureRating FOREIGN KEY (lessonID) REFERENCES lesson (lessonID) ON DELETE CASCADE
) ;

CREATE TABLE prerequisitesLookup (
classCode VARCHAR(20), -- FK class
preReqClassCode VARCHAR(20), -- FK class
PRIMARY KEY (classCode, preReqClassCode),
-- classCodereferences classCodein the class table
CONSTRAINT classCodeprerequisitesLookup FOREIGN KEY (classCode) REFERENCES class (classCode) ON DELETE CASCADE,
-- prereqID references prereqID in the class table
CONSTRAINT preReqIDprerequisitesLookup FOREIGN KEY (preReqClassCode) REFERENCES class (classCode) ON DELETE CASCADE
) ;

CREATE TABLE quiz (
quizID INT AUTO_INCREMENT,
timeLimit DOUBLE,
PRIMARY KEY (quizID)
) ;

CREATE TABLE quizUserScore (
quizID INT, -- FK quiz
userEmail VARCHAR(100), -- FK user
score DOUBLE,
PRIMARY KEY (quizID, userEmail),
-- quizID references quizID in the quiz table
CONSTRAINT quizIDquizUserScore FOREIGN KEY (quizID) REFERENCES quiz (quizID) ON DELETE CASCADE,
-- userEmail references userEmail in the user email
CONSTRAINT userEmailquizUSerScore FOREIGN KEY (userEmail) REFERENCES user (userEmail) ON DELETE CASCADE
) ;

CREATE TABLE classRating (
userEmail VARCHAR(100), -- FK user
classCode VARCHAR(20), -- FK class
rating INT,
PRIMARY KEY (userEmail, classCode),
-- userEmail references the userEmail in the user table
CONSTRAINT userEmailclassRating FOREIGN KEY (userEmail) REFERENCES user (userEmail) ON DELETE CASCADE,
-- classCodereferences the classCodein the class table
CONSTRAINT ratingClassRating FOREIGN KEY (classCode) REFERENCES class (classCode) ON DELETE CASCADE
) ;

CREATE TABLE quizQuestions (
quizID INT, -- FK quiz
questionNum INT,
questionContent TEXT,
questionAnswer TEXT,
PRIMARY KEY (quizID),
-- quizID references the quizID in the quiz table
CONSTRAINT quizIDquizQuestions  FOREIGN KEY (quizID) REFERENCES quiz(quizID) ON DELETE CASCADE
) ;

CREATE TABLE discussionGroups (
  discussionID INT AUTO_INCREMENT,
  groupName VARCHAR(25),
  userEmail VARCHAR(100) NOT NULL, -- FK user
  PRIMARY KEY (discussionID),
  CONSTRAINT userEmaildiscussionGroups FOREIGN KEY (userEmail) REFERENCES user(userEmail) ON DELETE CASCADE
) ;

CREATE TABLE discussionMessages (
  discussionMessageID INT AUTO_INCREMENT,
  discussionID INT, -- FK discussionGroups
  messages TEXT,
  userEmail VARCHAR(100) NOT NULL, -- FK user
  datePosted DATE,
  PRIMARY KEY (discussionMessageID),
  CONSTRAINT discussionIDdiscussionMessages FOREIGN KEY (discussionID) REFERENCES discussionGroups(discussionID) ON DELETE CASCADE,
  CONSTRAINT userEmaildiscussionMessages FOREIGN KEY (userEmail) REFERENCES user(userEmail) ON DELETE CASCADE
) ;

CREATE TABLE discussionGroupsMembers (
  discussionID INT NOT NULL, -- FK ID
  userEmail VARCHAR(100) NOT NULL, -- FK user
  PRIMARY KEY (discussionID, userEmail),
  CONSTRAINT discussionIDdiscussionGroupMembers FOREIGN KEY (groupName) REFERENCES discussionGroups(groupName) ON DELETE CASCADE,
  CONSTRAINT userEmaildiscussionGroupMembers FOREIGN KEY (userEmail) REFERENCES user(userEmail) ON DELETE CASCADE
) ;