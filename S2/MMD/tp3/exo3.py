import cv2
import numpy as np

# Charger l'image en niveaux de gris
gray_img = cv2.imread("image.jpg", cv2.IMREAD_GRAYSCALE)

# Définir un noyau (exemple : noyau de détection des contours)
kernel1 = np.array([[-1, -1, -1], [-1, 8, -1], [-1, -1, -1]])
kernel2 = np.array([[0, -1, 0], [-1, 5, -1], [0, -1, 0]])
kernel3 = np.array([[-1, 0, 1], [-2, 0, 2], [-1, 0, 1]])
kernel4 = np.array([[-2, -1, 0], [-1, 1, 1], [0, 1, 2]])
kernel5 = np.array([[1, 2, 1], [2, 4, 2], [1, 2, 1]]) / 16

kernels = [kernel1, kernel2, kernel4, kernel3, kernel5]

# Diviser l'image en 16 blocs
h, w = gray_img.shape
block_h, block_w = h // 4, w // 4
filtered_blocks = []

for i in range(4):
    row_blocks = []
    for j in range(4):
        block = gray_img[
            i * block_h : (i + 1) * block_h, j * block_w : (j + 1) * block_w
        ]

        # Sélection aléatoire du noyau
        kernel = kernels[np.random.randint(0, len(kernels))]

        filtered_block = cv2.filter2D(block, ddepth=cv2.CV_64F, kernel=kernel)
        filtered_block = cv2.convertScaleAbs(filtered_block)
        row_blocks.append(filtered_block)
    filtered_blocks.append(cv2.hconcat(row_blocks))

# Reconstituer l'image complète
final_image = cv2.vconcat(filtered_blocks)

# Afficher les images
cv2.imshow("Original", gray_img)
cv2.imshow("Filtered Image", final_image)
cv2.waitKey(0)
cv2.destroyAllWindows()
