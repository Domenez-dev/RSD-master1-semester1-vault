import cv2
import numpy as np

# Charger l'image en niveaux de gris
gray_img = cv2.imread("image.png", cv2.IMREAD_GRAYSCALE)
gray_img = cv2.resize(
    gray_img, (int(gray_img.shape[1] / 2), int(gray_img.shape[0] / 2))
)

# Définir un noyau (exemple : noyau de détection des contours)
kernel1 = np.ones((5, 5)) / 30
kernel2 = np.array([[-1, -1, -1], [-1, 8, -1], [-1, -1, -1]])


# Fonction de convolution
def convolution(pad_img, kernel):
    p = int(kernel.shape[0] / 2)
    pheight, pwidth = pad_img.shape
    img_conv = np.zeros((pheight, pwidth))
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
img_conv1_cv = cv2.convertScaleAbs(img_conv1_cv).astype(np.uint8)

img_conv2_cv = cv2.filter2D(src=gray_img, ddepth=cv2.CV_64F, kernel=kernel1)
img_conv2_cv = cv2.convertScaleAbs(img_conv2_cv).astype(np.uint8)

# Appliquer la convolution avec la fonction personnalisée
img_conv1_custom = convolution(pad_img, kernel2)
img_conv1_custom = cv2.convertScaleAbs(img_conv1_custom).astype(np.uint8)

img_conv2_custom = convolution(pad_img, kernel1)
img_conv2_custom = cv2.convertScaleAbs(img_conv2_custom).astype(np.uint8)

# Redimensionner toutes les images pour qu'elles aient la même taille
h, w = gray_img.shape
img_conv1_custom = cv2.resize(img_conv1_custom, (w, h))
img_conv2_custom = cv2.resize(img_conv2_custom, (w, h))

# Assurer que toutes les images sont de type uint8 et ont la même dimension
gray_img = gray_img.astype(np.uint8)

# Combiner les images horizontalement et verticalement
combined1 = cv2.hconcat([gray_img, img_conv1_cv, img_conv1_custom])
combined2 = cv2.hconcat([gray_img, img_conv2_cv, img_conv2_custom])
combined = cv2.vconcat([combined1, combined2])

# Afficher l'image combinée
cv2.imshow("Comparison", combined)
cv2.waitKey(0)
cv2.destroyAllWindows()
