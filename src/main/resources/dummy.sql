INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('abp6318@rit.edu', 'Aaron', 'Putterman', 'a', '0001', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('da7238@rit.edu', 'Darlene', 'Ardila', 'a', '0002', '1', 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('dxm6029@rit.edu', 'Dominique', 'Molee', 'a', '0003', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('vms4138@rit.edu', 'Vicky', 'Soler', 'a', '0004', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('admin@rit.edu', 'This is', 'the Admin', 'a', '0005', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('professor@rit.edu', 'Abdul', 'the Professor', 'p', '0006', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('antiprofessor@rit.edu', 'Evil Professor', 'Larry', 'p', '0007', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('anotherprofessor@rit.edu', 'Albert', 'Bananaman', 'p', '0008', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('student@rit.edu', 'Lazy Student', 'Leonard', 's', '0009', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('nevil@rit.edu', 'Nevil', 'Longbottom', 's', '0010', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('harry@rit.edu', 'Harry', 'Potter', 's', '0011', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('hermoine@rit.edu', 'Hermoine', 'Grainger', 's', '0012', 1, 'userPassword');
INSERT INTO `user` (`userEmail`, `fName`, `lName`, `accountType`, `verificationCode`, `verified`, `userPassword`) VALUES ('ron@rit.edu', 'Ron', 'Weasly', 's', '0013', 1, 'userPassword');

INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.140', 'admin@rit.edu', 'professor@rit.edu', 'Web 1', 'Learn the basics of web development', 'Students learned how to do HTML/CSS and some JavaScript', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.240', 'admin@rit.edu', 'professor@rit.edu', 'Web 2', 'Delve into the intermediate concepts of web development', 'Students learned more JavaScript, PHP, and phpMyAdmin', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.340', 'admin@rit.edu', 'antiprofessor@rit.edu', 'Client Programming', 'In depth JavaScript', 'Students are capable JavaScript developers', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.260', 'admin@rit.edu', 'antiprofessor@rit.edu', 'Designing for the User Experience', 'Learn about core design principles', 'Students understand basic design concepts', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('ISTE.252', 'admin@rit.edu', 'anotherprofessor@rit.edu', 'Foundations of Mobile Design', 'Learn how to design and develop for mobile', 'Students are now trained in prototyping and developing mobile applications (web, native, and hybrid)', '2021-01-25', '2021-05-08');
INSERT INTO `class` (`classCode`, `creatorEmail`, `professorEmail`, `className`, `learningObj`, `learningOutcome`, `beginDate`, `endDate`) VALUES ('SWEN.383', 'admin@rit.edu', 'anotherprofessor@rit.edu', 'Software Design Principles and Patterns', 'Learn how to work in teams and about SDLCs', 'Students implemented what they learned in a semester long project', '2021-01-25', '2021-05-08');

-- INSERT INTO `classGrade`

-- INSERT INTO `classListLookup` (`userEmail`, `classCode`, `lectureID`) VALUES ('userEmail', 'classCode', 'lectureID');

-- INSERT INTO `lectures`

-- INSERT INTO `lectureLookup`

-- INSERT INTO `lectureRating`

INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.240', 'ISTE.140');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.340', 'ISTE.240');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('ISTE.252', 'ISTE.260');
INSERT INTO `prerequisitesLookup` (`classCode`, `preReqClassCode`) VALUES ('SWEN.383', 'ISTE.240');

-- INSERT INTO `quiz`

-- INSERT INTO `quizUserScore`

INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'ISTE.340', 6);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'SWEN.383', 8);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('student@rit.edu', 'ISTE.252', 8);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'ISTE.340', 7);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'SWEN.383', 9);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('nevil@rit.edu', 'ISTE.252', 9);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'ISTE.340', 4);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'SWEN.383', 3);
INSERT INTO `classRating` (`userEmail`, `classCode`, `rating`) VALUES ('harry@rit.edu', 'ISTE.252', 1);

-- INSERT INTO `quizQuestions`

INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (20, 'SWEN DREAM TEAM', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (21, 'The evil team', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (22, 'UX Club', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (23, 'Localhost', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (24, 'Women in Computing', 'admin@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (25, 'Gamers', 'abp6318@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (26, 'Kicking Butt @ Interviews', 'vms4138@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (27, 'Outlander Fan Club', 'da7238@rit.edu');
INSERT INTO `discussionGroups` (`discussionID`, `groupName`, `userEmail`) VALUES (28, 'Spark Geniuses', 'dxm6029@rit.edu');

-- INSERT INTO `discussionMessages`

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (20, 'dxm6029@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (21, 'dxm6029@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (22, 'admin@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (23, 'admin@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'admin@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'abp6318@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'vms4138@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'da7238@rit.edu');
INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (24, 'dxm6029@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (25, 'abp6318@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (26, 'vms4138@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (27, 'da7238@rit.edu');

INSERT INTO `discussionGroupsMembers` (`discussionID`, `userEmail`) VALUES (28, 'dxm6029@rit.edu');

