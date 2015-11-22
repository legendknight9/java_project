<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<title>Please give us your opinion</title>
<s:head/>
</head>
<body>

<s:form action="feedbackaction" >
	<div align="center">
		<s:radio name="question1" list="answer" listKey="answer" listValue="answerDescription" 
		label="1. The course objectives were met (Mục tiêu khóa học đã đạt được)" labelposition="top"/>
		<s:textfield name="note1" />
		
		<s:radio name="question2" list="answer" listKey="answer" listValue="answerDescription" 
		label="2. The level of the course is appropriate (Khóa học phù hợp với trình độ của học viên)"
		labelposition="top" />
		<s:textfield name="note2" />
	
		<s:radio name="question3" list="answer" listKey="answer" listValue="answerDescription"
		label="3. This course will be useful for my work (Khóa học bổ ích cho công việc của bạn)"
		labelposition="top"/>
		<s:textfield name="note3" />
	
		<s:radio name="question4" list="answer" listKey="answer" listValue="answerDescription"
		label="4. The training materials were good (Tài liệu giảng dạy chuẩn bị tốt)" labelposition="top"/>
		<s:textfield name="note4" />
	
		<s:radio name="question5" list="answer" listKey="answer" listValue="answerDescription"
		label="5. The organization of the course (facility) was good (Cơ sở vật chất và công tác tổ chức tốt)"
		labelposition="top"/>
		<s:textfield name="note5" />
	
		<s:radio name="question6" list="answer" listKey="answer" listValue="answerDescription"
		label="6. Subject coverage is good with respect to courseware (Đảm bảo đủ nội dung c/t học)"
		labelposition="top"/>
		<s:textfield name="note6" />
	
		<s:radio name="question7" list="answer" listKey="answer" listValue="answerDescription"
		label="7. Your questions/doubts were answered satisfactorily and teacher support is good (Giảng viên hỗ trợ và giải đáp thắc mắc tốt cho học viên)"
		labelposition="top"/>
		<s:textfield name="note7" />
	
		<s:radio name="question8" list="answer" listKey="answer" listValue="answerDescription" 
		label="8. The quality of trainer instruction and communication was good. (Kỹ năng sư phạm của GV tốt)"
		labelposition="top"/>
		<s:textfield name="note8" />
	
		<s:submit />
	</div>
</s:form>

</body>
</html>