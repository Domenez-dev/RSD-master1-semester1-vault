import cv2
import numpy as np
import matplotlib.pyplot as plt

image_path = "img.jpg"
image = cv2.imread(image_path, 0)

h, w = image.shape

h_pad = (8 - (h % 8)) % 8
w_pad = (8 - (w % 8)) % 8

padded_image = np.pad(image, ((0, h_pad), (0, w_pad)), "constant")

fq = 100

quant = np.fromfunction(lambda i, j: 1 + (1 + i + j) * fq, (8, 8), dtype=int)

newImage = padded_image.copy()

for i in range(0, newImage.shape[0], 8):
    for j in range(0, newImage.shape[1], 8):
        block = newImage[i : i + 8, j : j + 8]
        blockDct = cv2.dct(np.float32(block))

        # dct
        blockDct = blockDct / quant
        blockDct = blockDct.astype(int)

        # idct
        blockDst = blockDct * quant
        blockDst = np.float32(blockDst)
        blockDst = cv2.idct(blockDst)
        newImage[i : i + 8, j : j + 8] = blockDst

newImage = newImage[:h, :w]

plt.figure(figsize=(10, 5))
plt.subplot(1, 2, 1)
plt.imshow(image, cmap="gray")
plt.title("Image Originale")
plt.axis("off")

plt.subplot(1, 2, 2)
plt.imshow(newImage, cmap="gray")
plt.title(f"Image après compression (fq={fq})")
plt.axis("off")
plt.show()
