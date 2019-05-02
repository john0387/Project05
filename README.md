# Project05
1. This project was made all in one class with the constructor, 
accompanying methods, and the main function encompassing everything.

2. At the top of my file are all of my variables including a 
GridBagConstraint, my GUIs, a String array to hold the different codes 
from Mesonet.txt, and more. Since I squeezed everything onto one file, 
the number of variables present is a little overwhelming.

3. The first thing done in the constructor is a call to the read method, 
which I copy-pasted from project1. This went line-by-line through 
Mesonet.txt and put each code into a String arrayList called IDlist.

4. Next in the constructor, I initialized each of my GUI components, 
adding action listeners for the buttons and a change listener for the 
slider

5. After initializing each component, I went ahead and added all of them 
to the frame, taking care of all the formatting using a gridbaglayout

6. That ends the constructor, and next is the read method, which, as 
stated above was simply recylced from project1

7. The next method is called ChangeTextToSlider. This method's only 
purpose is to sync up the topmost textbox with the number that the 
slider displays. It is only one line of code, but it does work.

8. Next is a method called expandDropDownList. Note that this method 
does NOT work, but if it was implemented properly, it was supposed to 
react to the the add station button was pressed and add the content of 
the textbox right next to it into he dropdown bar, hence the name 
expandDropDownList.

9. The next method is called HammedSliderStations. This method receives 
the currently selected ID from the dropdown bar and displays all of the 
IDs that have a hammingdistance equal to the one shown in the slider. 
Note that if the list gets really long, a scrollbar will appear.

10. Next is a method called CalcHD. The name of the method is a gives 
away its purpose, it is linked to the Calculate HD button and it does 
just that, it calculate the haming distances from 0-4 of the whole list 
compared to the ID currently selected in the dropdown bar.

11. Last but not least is the main method. In here is simply an 
initialization of a HammingGUI object and some properties that are set 
onto it including the default close operation, auto-resizing, and of 
course seeting the visibility to true.
