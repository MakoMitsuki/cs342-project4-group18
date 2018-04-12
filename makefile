sources = $(wildcard *.java)
classes = $(sources:.java=.class)

all: myProgram

myProgram: $(classes)

clean:
	rm  -f *.class

%.class : %.java
	javac $<

ExamBuilder: $(classes)
	jar cvmf manifest.txt ExamBuilder.jar ExamBuilder.class ScannerFactory.class Exam.class Answer.class Question.class MCAnswer.class NumAnswer.class SAAnswer.class MCSAAnswer.class MCMAAnswer.class MCQuestion.class MCSAQuestion.class MCMAQuestion.class NumQuestion.class SAQuestion.class

ExamGrader: $(classes)
	jar cvmf manifest2.txt ExamGrader.jar ExamGrader.class ScannerFactory.class Exam.class Answer.class Question.class MCAnswer.class NumAnswer.class SAAnswer.class MCSAAnswer.class MCMAAnswer.class MCQuestion.class MCSAQuestion.class MCMAQuestion.class NumQuestion.class SAQuestion.class

ExamTaker: $(classes)
	jar cvmf manifest3.txt ExamTaker.jar ExamTakerMain.class ScannerFactory.class Exam.class Answer.class Question.class MCAnswer.class NumAnswer.class SAAnswer.class MCSAAnswer.class MCMAAnswer.class MCQuestion.class MCSAQuestion.class MCMAQuestion.class NumQuestion.class SAQuestion.class
