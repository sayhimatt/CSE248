   ╔╦╗┌─┐┌┬┐┌┬┐  ╔═╗┬ ┬┬┌┬┐┬┌─┐             
    ║║║├─┤ │  │   ║ ╦│ ││ │││└─┐             
    ╩ ╩┴ ┴ ┴  ┴   ╚═╝└─┘┴─┴┘┴└─┘             
  ╔═╗┌─┐┬  ┬  ┌─┐┌─┐┌─┐  ╔═╗┌─┐┌─┐┬─┐┌─┐┬ ┬  
  ║  │ ││  │  ├┤ │ ┬├┤   ╚═╗├┤ ├─┤├┬┘│  ├─┤  
  ╚═╝└─┘┴─┘┴─┘└─┘└─┘└─┘  ╚═╝└─┘┴ ┴┴└─└─┘┴ ┴  
      ╔═╗┌─┐┌─┐┬  ┬┌─┐┌─┐┌┬┐┬┌─┐┌┐┌          
      ╠═╣├─┘├─┘│  ││  ├─┤ │ ││ ││││          
      ╩ ╩┴  ┴  ┴─┘┴└─┘┴ ┴ ┴ ┴└─┘┘└┘    
----------------------------------------------------------------------------------------
╔╦╗┌─┐┌┬┐┬ ┬┬  ┌─┐  ╔═╗┌┐┌┌─┐           
║║║│ │ │││ ││  ├┤   ║ ║│││├┤            
╩ ╩└─┘─┴┘└─┘┴─┘└─┘  ╚═╝┘└┘└─┘           
╔╦╗┌─┐┌┬┐┌─┐  ╦═╗┌─┐┌┬┐┬─┐┬┌─┐┬  ┬┌─┐┬  
 ║║├─┤ │ ├─┤  ╠╦╝├┤  │ ├┬┘│├┤ └┐┌┘├─┤│  
═╩╝┴ ┴ ┴ ┴ ┴  ╩╚═└─┘ ┴ ┴└─┴└─┘ └┘ ┴ ┴┴─┘

Live data is retrieved via Volley.
It is processed into a Java object before it is then 
submitted into the Firebase Database using the IPEDS ID 
as a key. Live data can be thrown into the database
with the press of a button on the Account screen*.
----------------------------------------------------------------------------------------
╔╦╗┌─┐┌┬┐┬ ┬┬  ┌─┐  ╔╦╗┬ ┬┌─┐  ┌─┐┌┐┌┌┬┐  ╔╦╗┌─┐┌┬┐┬ ┬┬  ┌─┐  ╔╦╗┬ ┬┬─┐┌─┐┌─┐
║║║│ │ │││ ││  ├┤    ║ ││││ │  ├─┤│││ ││  ║║║│ │ │││ ││  ├┤    ║ ├─┤├┬┘├┤ ├┤ 
╩ ╩└─┘─┴┘└─┘┴─┘└─┘   ╩ └┴┘└─┘  ┴ ┴┘└┘─┴┘  ╩ ╩└─┘─┴┘└─┘┴─┘└─┘   ╩ ┴ ┴┴└─└─┘└─┘
╦═╗┌─┐┌─┐┬┌─┐┌┬┐┬─┐┌─┐┌┬┐┬┌─┐┌┐┌  ┌─┐┌┐┌┌┬┐  ╦  ┌─┐┌─┐┬┌┐┌                   
╠╦╝├┤ │ ┬│└─┐ │ ├┬┘├─┤ │ ││ ││││  ├─┤│││ ││  ║  │ ││ ┬││││                   
╩╚═└─┘└─┘┴└─┘ ┴ ┴└─┴ ┴ ┴ ┴└─┘┘└┘  ┴ ┴┘└┘─┴┘  ╩═╝└─┘└─┘┴┘└┘      				
New users can register a new account and use that for access into the app.
A planned feature is to have modifiable SAT scores and modifiable names.
The users' passwords are securely stored inside Firebase's Authentication system.
The profile information is stored in the same database as the schools.
PASSWORDS ARE NOT STORED IN THE USER DATABASE. Only source of a password is
when the user is registering/logging in which is masked. Kind of self explanatory 
but Firebase Authentication is used to authenticate the user's login as well.
----------------------------------------------------------------------------------------
╔╦╗┌─┐┌┬┐┬ ┬┬  ┌─┐  ╔═╗┌─┐┬ ┬┬─┐                                         
║║║│ │ │││ ││  ├┤   ╠╣ │ ││ │├┬┘                                         
╩ ╩└─┘─┴┘└─┘┴─┘└─┘  ╚  └─┘└─┘┴└─                                         
╔═╗┌─┐┬  ┬  ┌─┐┌─┐┌─┐  ╔═╗┌─┐┌─┐┬─┐┌─┐┬ ┬  ╔═╗┌─┐┌─┐┌─┐┌┐ ┬┬  ┬┌┬┐┬┌─┐┌─┐
║  │ ││  │  ├┤ │ ┬├┤   ╚═╗├┤ ├─┤├┬┘│  ├─┤  ║  ├─┤├─┘├─┤├┴┐││  │ │ │├┤ └─┐
╚═╝└─┘┴─┘┴─┘└─┘└─┘└─┘  ╚═╝└─┘┴ ┴┴└─└─┘┴ ┴  ╚═╝┴ ┴┴  ┴ ┴└─┘┴┴─┘┴ ┴ ┴└─┘└─┘
The search fragment of the app allows the user to type in the name of the college
that they were interested in going to. With an autocompleting spinner to assist with any
special names it may or may not have. Along with the choice of selecting colleges with a name,
the user can specify the colleges even further by choosing the degree that they would prefer to see.
When selecting on any of the four checkboxes it will look to see if the college offers it as a primary option
or as their highest award given. This can be helpful for weeding out trade school where they fail to offer a typical degree.
The following search functions are a WIP and I don't feel comfortable saying they are complete by any means:
A distance from ZIP query, A filter of colleges via State residence.
Once the users confirms their search option they can select the results fragment
to observe any results that met their criteria.
By tapping on any of the college names a user can see the following info:
1. The school's unique ID
2. The school's home state
3. School Zip
4. School's Region (Output into regular text):
5. Admissions Rate |in the form of a percentage, if not provided we assume 100% :)  |
6. Cost In state
7. Cost out of state
8. Primary Degree that the school awards
9. Highest Degree that the school awards
10. School URL (if provided)
11. Size of student body at school
The rest are all SAT scores which brings us to the next part!
----------------------------------------------------------------------------------------
╔╦╗┌─┐┌┬┐┬ ┬┬  ┌─┐  ╔═╗┬┬  ┬┌─┐                              
║║║│ │ │││ ││  ├┤   ╠╣ │└┐┌┘├┤                               
╩ ╩└─┘─┴┘└─┘┴─┘└─┘  ╚  ┴ └┘ └─┘                              
╔═╗┌─┐┌─┐┌─┐┌─┐┌┬┐┌─┐┌┐┌┌─┐┌─┐  ╔═╗┬─┐┌─┐┌┐ ┌─┐┌┐ ┬┬  ┬┌┬┐┬ ┬
╠═╣│  │  ├┤ ├─┘ │ ├─┤││││  ├┤   ╠═╝├┬┘│ │├┴┐├─┤├┴┐││  │ │ └┬┘
╩ ╩└─┘└─┘└─┘┴   ┴ ┴ ┴┘└┘└─┘└─┘  ╩  ┴└─└─┘└─┘┴ ┴└─┘┴┴─┘┴ ┴  ┴ 
Previously stated was how the SAT scores are engrained into the Alert for
each School's info. The user's own info is also injected for them to compare.
Now the info taken from the user is for the new SAT and a lot of the college info is old.
So to "convert" the scores I took the Math score as is and set writing and english to their 
combined score respectively in the new one. I then compare use a point calculator. If the
user has a score below the 25th percentile I take off 2 quality points. If they score within 25th
percentile then I give them 1 point. If they are higher than the 75th they get 2 points.
I do this for each one and give them a generic response. In the end these are just scores.
If it's negative they are told that their chances of getting in are pretty bad.
Anything that just got a little positive [0, 3] has an about average change like others.
Higher than that allows a greater chance than normal and is reflected in the output.
----------------------------------------------------------------------------------------




















