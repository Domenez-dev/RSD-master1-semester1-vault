import cv2
import numpy as np
import matplotlib.pyplot as plt

'''
    Partie I) OpenCV
'''

# Read the image
img = cv2.imread('image.png')
# img = cv2.imread('random.jpg', cv2.IMREAD_COLOR)
print (type(img))
# Get the image height and width
height, width, channels = img.shape
print (f'height: {height} / width: {width} / channels: {channels}')
# Extract the RGB values of a pixel
B, G, R = img[200, 250]
print (f'R: {R}, G: {G}, B: {B}')

'''
    I.b) Resize image
'''
# Resize the images
resized_img = cv2.resize(img, (int(img.shape[1]/2), int(img.shape[0]/2)))
# Save the image
cv2.imwrite("resized.png", resized_img)

'''
    Partie II) numpy
'''
# Convert from BGR to YCrCb color space: Y is the Luminance component, Cb and Cr chrominance components
img_ycrcb = cv2.cvtColor(resized_img, cv2.COLOR_BGR2YCrCb)
cv2.imwrite("ycrcb_random.png", img_ycrcb)
# Convert from BGR to HSV color space
img_hsv = cv2.cvtColor(resized_img, cv2.COLOR_BGR2HSV)
cv2.imwrite("hsv_random.png", img_hsv)
# Convert an RGB image to grayscale
img_gray = cv2.cvtColor(resized_img, cv2.COLOR_BGR2GRAY)
# concatenate image Horizontally
images = np.concatenate((cv2.cvtColor(img_gray, cv2.COLOR_GRAY2BGR), img_ycrcb, img_hsv), axis=1)
'''
    Display the Image
'''
RGB_img = cv2.cvtColor(resized_img, cv2.COLOR_BGR2RGB)
cv2.imshow("Resized image", RGB_img)

# Hold the screen until the user closes it.
cv2.waitKey(0)
# Destroy the window
cv2.destroyAllWindows()
