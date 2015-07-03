CREATE TABLE STUDY_GROUP (
  id INT NOT NULL AUTO_INCREMENT,
  study_name VARCHAR(20) NOT NULL,
  study_desc VARCHAR(1000) NOT NULL,
  leader_id VARCHAR(20) NOT NULL,
  create_at DATETIME ,
  update_at DATETIME ,
  PRIMARY KEY (id),
  UNIQUE (study_name)
);

INSERT INTO STUDY_GROUP (id,study_name,study_desc,leader_id)
VALUES (1,'�ڹ�������!','�������ڹ٤��ٹٹ�����������','NGS');

