# Linker
Lightweight android library for highlighting Strings inside of a textview (ignoring case), with optional callbacks.
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Linker-green.svg?style=flat )]( 
https://android-arsenal.com/details/1/7613 )
[![](https://jitpack.io/v/gaineyj0349/Linker.svg)](https://jitpack.io/#gaineyj0349/Linker)
![alt text](https://github.com/Gaineyj0349/Linker/blob/master/linker.png)

Language: Java

MinSDK: 17

JavaDocs: www.gainwisetech.com/javadocs/Linker



To bring into your android project implement the artifact:

In the Project level build.gradle
```java
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

In the App level build.gradle
```java
       dependencies {
	        implementation 'com.github.Gaineyj0349:Linker:1.2'
	}
```




How to use:

1 - Construct a Linker object with a textview:
```java
    	Linker linker = new Linker(textView);
```    
2 - Add an array or a list of strings to be highlighted within the textview's text:
```java
        ArrayList<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        linker.addStrings(list);
```    
AND/OR
```java
        String[] words = new String[]{"One", "Two", "Three"};
        linker.addStrings(words);
```  
3 - Add a callback: (this is optional):
```java
       linker.setListener(new LinkerListener() {
            @Override
            public void onLinkClick(String charSequenceClicked) {
            
                // charSequenceClicked is the word that was clicked
                
                Toast.makeText(MainActivity.this, charSequenceClicked, Toast.LENGTH_SHORT).show();
            }
        });
```      
4 - Call the linker's update method to commit customization and rollout the setup.:
```java  
    	linker.update();
```   
    
     
 
 You always have the option to add Strings to the linker object, just make sure you call the update method after to refresh the spans.
```java
	linker.addStrings("yoda");
	linker.update();
```

If you need a fresh slate with same linker object just call 
```java
       linker.clearLinksList()
```  


 You can customize the links also:

 1 - Customize all the link colors:
```java  
      linker.setAllLinkColors(Color.BLUE);
```     
 2 - Customize link underlines:
```java 
      linker.setAllLinkUnderline(false);
```   
 3 - If you wish to customize a color or underline setting for a certain string (note the string must already be added to the linker):
```java
      linker.setLinkColorForCharSequence("world", Color.MAGENTA);
      linker.setUnderlineModeForCharSequence("world", true);
```    
 4 - If you wish to use different setups for every word then you can also give the linker object a list or array of LinkProfiles:
```java     
        ArrayList<LinkProfile> profiles = new ArrayList<>();
        profiles.add(new LinkProfile("hello world",
                Color.GREEN, false));
        profiles.add(new LinkProfile("goodbye cruel world",
                Color.RED, false));
        profiles.add(new LinkProfile("Whoa awesome!",
                Color.CYAN, true));

        
        linker.addProfiles(profiles);
```      

Just remember to call .update() after any additions to the linker object.
 

Note that the library will take care of subtleties like adding two of the same words, or same parts of a word. For example if "helloworld" and "hello" are two of the words added to the linker, "helloworld" will be given preference over "hello" when they are in the same span of characters. The linker will sort according to larger words first and trace all spans as it links them - avoiding the issue of duplication as well as intersecting spans.
     

Licensed under [MIT license](LICENSE) .
