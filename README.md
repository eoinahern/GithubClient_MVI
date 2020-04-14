MVI Gihub Client


- A means of studying different implementations of MVI.
- Seperate branches will exist which will investigate different styles of MVI implementation.
- I intend to copy first the example on the raywendelich.com MVI course. Followed by Kaushik Gopal's
  version that he previously mentioned on the Fragmented podcast. And finally I will Implement the MVI
  example created in the Caster.io course.
- All implementations are slightly different hopefully from playing around with each on I will find
  the implementation I like the most or create my own hybrid of the ideas in each.
  
  
  
 Master branch (ray wenderlich tutorial course)
 
 - utilized the scan function from rx. making out ViewState immutable.
 - proccessholder and viewmodel architecture components.
 - a lot of rewritng and verbosity which slowed me down.
 - maybe useful in a bigger application. but the lack of simplicity obfuscated my understanding 
   of the pattern. which, made it harder to work with.
 - violates DRY quite a bit. 
 - doesnt adhere to KISS either
   
 ray_wenderlich_book_example branch (Advanced android app architectures book)
 
 - utilized MVI also. used Presenter instead of a viewModel. 
 - sealed class for viewState, made organizing the view slightly cleaner in activity/view
 - less verbosity, easy to understand. Implemented quickly. 
 - removed a large amount of unnecessary classes when using this architecture after implementing branch.
 - more decoupled. I prefer this arch to the Master branch but it may no be considered "proper" MVI as it didnt add 
   loads of unneccessary layers of abstraction and donkey work for the developer. 
 - utilized Rx, Retrofit, Dagger2. 
