This application implies a system that interacts with the database, by means of predefined methods. 
There are two kinds of profiles, a librarian and a student. The librarian can add, delete books. 
The student in turn cannot do what the librarian can do. The student can take the book and return it after a time. 
The moment a student takes a book from the library, 
an entry is created in another table that indicates what book and who exactly took it from the library. 
When an entry is created in this table, in the table books changes the availability of the book the student picked up to false. 
And this application also has the ability to display data from the database, 
show all existing accounting of who took a book from the library or for example the student 
can see in his profile what accountings he has active.  
You can find and display data about a book by its ID number or name. You can also display the entire list of books. 
The librarian and student Classes share common methods, and the human interface was originally created for them, 
from which the student and librarian were inherited.