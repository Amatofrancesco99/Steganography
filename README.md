# Steganography
An application in order to apply steganography to images, using threads.

[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://github.com/Amatofrancesco99/Steganography/blob/main/LICENSE)&emsp;
![Java](https://img.shields.io/badge/Backend-Java-red)&emsp;
![Swing](https://img.shields.io/badge/Frontend-Java_Swing-green)

## Meaning
Steganography is the practice of concealing a message within another message or a physical object.
In computing/electronic contexts, a computer file, message, image, or video is concealed within another file, message, image, or video.
The word steganography comes from Greek _steganographia_, which combines the words _steganós (στεγανός)_, meaning _"covered or concealed"_, and _graphia (γραφή)_ meaning _"writing"_.

With **least significant bit** steganography, the approach revolves around changing the least significant bit of each pixel's RGB values to match a corresponding bit in the message we want to encode. This way, the change is so small that the encoded image won't be noticably different to the naked eye from the original.

## Usage
The application has a graphical user interface, which is developed with [_Java Swing_](https://www.javatpoint.com/java-swing).
After downloaded the files, run the [GUIMain.java](https://github.com/Amatofrancesco99/Steganography/blob/main/src/main/java/steganography/view/gui/GUIMain.java) class.
If you choose: 
 - `ENCODE` you can apply steganography to an image (hide the message). You have to:
   - choose the image on which steganography will be applied;
   - insert the name of the steganographed image;
   - add the _hidden message_. 
- `DECODE` you can read the message from a steganographed image.
