JFLAGS = -g 
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Bus.java \
	Driver.java \
	Passenger.java \
	Suitcase.java \
	Terminal.java \
	Conductor.java \
	TicketSeller.java \
	Main.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
