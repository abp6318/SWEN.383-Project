DROP DATABASE IF EXISTS mypls;
CREATE DATABASE mypls;
USE mypls;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userEmail VARCHAR(100) NOT NULL,
  fName VARCHAR(75) NOT NULL,
  lName VARCHAR(75) NOT NULL,
  accountType TINYINT (3),
  verified BOOLEAN NOT NULL,
  userPassword VARCHAR(20) NOT NULL ,
  PRIMARY KEY (userEmail),

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classGrade (
userEmail VARCHAR(100),
classID INT NOT NULL,
grade DOUBLE,
PRIMARY KEY (userEmail, classID),
CONSTRAINT `foreign key` FOREIGN KEY (`classID`) REFERENCES `class` (`classID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classListLookup (
userEmail VARCHAR(100),
classID INT,
lectureID INT NOT NULL,
grade DOUBLE,
PRIMARY KEY (userEmail, classID),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE class (
classID int AUTO_INCREMENT,
creatorEmail VARCHAR (100) NOT NULL,
professorEmail VARCHAR(100)NOT NULL,
className VARCHAR(100) NOT NULL,
classCode VARCHAR(20) NOT NULL,
learningObj TEXT,
leanringOutcome TEXT,
beginDate DATE,
endDate DATE,
PRIMARY KEY (classID),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectureLookup (
classID INT,
lectureID INT NOT NULL,
PRIMARY KEY (classID, userID),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectures (
lectureID INT,
multimediaID INT, -- datatype sub until we find one for files and stuff
PRIMARY KEY (lectureID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE lectureRating (
userEmail VARCHAR (100),
lectureID INT, 
PRIMARY KEY (userEmail, lectureID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE prerequisitesLookup (
classID INt,
preReqID INT,
PRIMARY KEY (classID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quizUserScore (
quizID INT AUTO_INCREMENT,
userEmail VARCHAR(100),
quizID INT,
score DOUBLE,
PRIMARY KEY (quizID, userEmail)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quiz (
quizID INT,
timeLimit DOUBLE,
PRIMARY KEY (quizID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE classRating (
userEmail VARCHAR(100),
classID INT,
rating INT
PRIMARY KEY (userEmail, classID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE quizQuestions (
quizID INT,
quizQuestionID INT AUTO_INCREMENT,
questionNum INT,
questionContent TEXT,
questionAnswer TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






