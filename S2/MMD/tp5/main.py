import cv2
import numpy as np
import matplotlib.pyplot as plt

image_path = "img.jpg"
image = cv2.imread(image_path, 0)

if image is None:
    print("Erreur : Impossible de charger l'image.")
    exit()

image_float32 = np.float32(image)
dct = cv2.dct(image_float32)  # type: ignore
dctShow = np.log(1 + np.abs(dct))

image2 = cv2.idct(dct)
image2 = np.uint8(image2)


plt.figure(figsize=(15, 5))

plt.subplot(1, 4, 1)
plt.imshow(image, cmap="gray")
plt.title("Image Originale")
plt.axis("off")

plt.subplot(1, 4, 2)
plt.imshow(dct, cmap="gray")
plt.title("DCT de l'image")
plt.axis("off")


plt.subplot(1, 4, 3)
plt.imshow(dctShow, cmap="gray")
plt.title("DCT (avec log)")
plt.axis("off")


plt.subplot(1, 4, 4)
plt.imshow(image2, cmap="gray")
plt.title("Image Reconstruite")
plt.axis("off")

plt.tight_layout()
plt.show()
