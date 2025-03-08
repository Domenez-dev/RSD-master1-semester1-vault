import cv2
import numpy as np

# Charger l'image en niveaux de gris
gray_img = cv2.imread("image.jpg", cv2.IMREAD_GRAYSCALE)
gray_img = cv2.resize(
    gray_img, (int(gray_img.shape[1] / 2), int(gray_img.shape[0] / 2))
)

# Définir un noyau (exemple : noyau de détection des contours)
kernel1 = np.ones((5, 5)) / 30
kernel2 = np.array([[-1, -1, -1], [-1, 8, -1], [-1, -1, -1]])


# Fonction de convolution
def convolution(pad_img, kernel):
    pad_img = cv2.convertScaleAbs(pad_img)

    p = int(kernel.shape[0] / 2)
    pheight, pwidth = pad_img.shape
    img_conv = np.zeros(pad_img.shape)
    for i in range(p, pheight - p):
        for j in range(p, pwidth - p):
            roi = pad_img[i - p : i + p + 1, j - p : j + p + 1]
            img_conv[i, j] = np.sum(kernel * roi)
    img_conv = img_conv[p:-p, p:-p]
    return img_conv


# Ajouter un padding à l'image
p = int(kernel1.shape[0] / 2)
pad_img = np.zeros((gray_img.shape[0] + 2 * p, gray_img.shape[1] + 2 * p))
pad_img[p:-p, p:-p] = gray_img

# Appliquer la convolution avec OpenCV
img_conv1_cv = cv2.filter2D(src=gray_img, ddepth=cv2.CV_64F, kernel=kernel2)
img_conv1_cv = cv2.convertScaleAbs(img_conv1_cv)
img_conv2_cv = cv2.filter2D(src=gray_img, ddepth=cv2.CV_64F, kernel=kernel1)
img_conv2_cv = cv2.convertScaleAbs(img_conv2_cv)

# Appliquer la convolution avec la fonction personnalisée
img_conv1_custom = convolution(pad_img, kernel2)
img_conv1_custom = cv2.convertScaleAbs(img_conv1_custom)
img_conv2_custom = convolution(pad_img, kernel1)
img_conv2_custom = cv2.convertScaleAbs(img_conv2_custom)

# Afficher les images
cv2.imshow("1- original", img_conv1_cv)
cv2.imshow("1- personnalise", img_conv1_custom)
cv2.imshow("2- original", img_conv2_cv)
cv2.imshow("2- personnalise", img_conv2_custom)
cv2.waitKey(0)
cv2.destroyAllWindows()
