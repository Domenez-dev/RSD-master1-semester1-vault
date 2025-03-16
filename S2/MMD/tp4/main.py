import cv2
import numpy as np


def rle_decode(encoded):
    decoded = []
    index = 0
    while index < len(encoded):
        code = encoded[index]
        if code >= 128:

            count = code - 128
            value = encoded[index + 1]
            decoded.extend([value] * count)
            index += 2
        else:

            length = code
            literals = encoded[index + 1 : index + 1 + length]
            decoded.extend(literals)
            index += 1 + length
    return decoded


def decode_to_image(input_txt_path, output_image_path, image_shape):
    with open(input_txt_path, "r") as file:
        encoded_data = list(map(int, file.read().split()))

    binary_array = rle_decode(encoded_data)

    binary_image = np.array(binary_array).reshape(image_shape)

    reconstructed_image = np.where(binary_image == 1, 255, 0).astype(np.uint8)

    cv2.imwrite(output_image_path, reconstructed_image)
    print(f"Reconstructed image saved to {output_image_path}")


input_txt_path = "result.txt"
output_image_path = "result.jpg"
image_shape = (480, 512)

decode_to_image(input_txt_path, output_image_path, image_shape)
