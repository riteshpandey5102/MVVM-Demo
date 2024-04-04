__Architecture Pattern__  
Architectural patterns provide a structured approach for organizing code and separating concerns, making it easier to manage complexity and promote code reusability.

__Why does your app need good architecture?__  
A simple answer is that everything should be organized in a proper way. So, the project becomes easily testable, scalable, manageable, robust and modular.  
`If we don’t use a right architecture pattern to build our project, we will face many issues such as-`
- __Difficult to test -__ Since large codes are unorganized so it’s difficult to completely test any single component.
- __Difficult to maintain -__ Unorganized code make difficult to keep track of the methods inside a class which makes difficult to improve or maintain it.
- __Difficult to scale -__ Unorganized code doesn’t have proper modularity which makes difficult to understand the existing features and add new features to it.

__There are 3 architecture pattern to organize our code__
- [Model view controller](https://github.com/riteshpandey5102/MVC-Demo)
- [Model view presenter](https://github.com/riteshpandey5102/MVP-Demo)
- [Model view viewmodel](https://github.com/riteshpandey5102/MVVM-Demo)

__Model View ViewModel__  
In this project we will learn about the first pattern Model view viewmodel   
__Model:__ It simply gets data from data source and publish the result with help of observables. It is not depended on view or view model.  
__View:__ It simply subscribes the observables in view model and perform UI actions on event triggered on viewmodel observables. It is only depended on view model.  
__View Model:__ It is used to get the data from model and apply the required logic on it then provide it to view with the help of observables. It is only depended on model. Also it is lifecycle aware so it will not loss data on Lifecyle events like orientation changes.   
![Model View ViewModel](https://github.com/riteshpandey5102/MVVM-Demo/blob/main/MVVM.jpg?raw=true)  
Here in mvvm data flow is unidirectional so View is only depended on Viewmodel, View Model is only depended on Model and Model is independent it doesn’t have any dependency. 
So, it removes the circular dependency which makes it easier to test and maintain the project.  

__Problems in MVVM__  
- It doesn’t have any major problem just need to learn reactive programming to use it which makes it little difficult to understand it. 

__More Links__  
__[Model view controller](https://github.com/riteshpandey5102/MVC-Demo)__  
__[Model view presenter](https://github.com/riteshpandey5102/MVP-Demo)__

