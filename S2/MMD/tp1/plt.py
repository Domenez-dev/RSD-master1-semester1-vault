import cv2
import numpy as np
import matplotlib
matplotlib.use('tkagg')

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
    Partie III) Matplotlib
'''
# Converting BGR color to RGB color format
RGB_img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
# Display the image using plt
plt.imshow(RGB_img)
# Show without the axes
plt.axis('off')
# Put a title
plt.title('Image')
plt.show()


R_img = np.zeros_like(img)
G_img = np.zeros_like(img)
B_img = np.zeros_like(img)
# assigner la valeur de chaque canal a la composante correspondante en laissant les autres canaux a 0
R_img[:, :, 0] = R # Canal Rouge
G_img[:, :, 1] = G # Canal Vert
B_img[:, :, 2] = B # Canal Bleu

'''
    Partie 4) Adding Borders
'''
padded_image = np.pad(RGB_img, pad_width=((10, 10), (10, 10), (0, 0)), mode='constant', constant_values=0)
figure, plot = plt.subplots(nrows=1, ncols=2)
plot[0].imshow(RGB_img)
plot[0].set_title('l’image originale')
plot[0].axis('off')
plot[1].imshow(padded_image)
plot[1].set_title('apres le padding')
plot[1].axis('off')
plt.show()

'''
    Partie 5) Colored
'''
padded_image[:, :100, 0] = 255
padded_image[:100, :, 1] = 255
padded_image[:, width-100:, 2] = 255

plt.imshow(padded_image)
plt.axis('off')
plt.show()
