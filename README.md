# GiniLibrary

An Android library to pick a cat image from http://thecatapi.com/.

### Features ?
1. Displaying a list of cat pictures parsed from the The Cat API
3. Allowing customizations:
   - The theme (including the action bar color)
   - The action bar’s title
   - The back button background color
   
### How do I use it?
You have to :
1. Initialize the library settings
 * Example :
    - var settings = CatImageLibSettings().apply{
        appBarTitle = "GiniLibrary"
        appBarColor = R.style.Theme_Red
        backButtonSRC = android.R.drawable.arrow_up_float
    }
2. Implement CatImageListener Callback in the Parent in order to retrieve the selected picture url 
3. Launch the cat list screen 
 -   CatImageLib.openCatList(requireActivity(), this, settings!!)
