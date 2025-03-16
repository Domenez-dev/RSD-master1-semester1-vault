import cv2
import numpy as np


def rle(image):
    if not image:
        return []

    runs = []
    current_value = image[0]
    count = 1
    for pixel in image[1:]:
        if pixel == current_value:
            count += 1
        else:
            runs.append((current_value, count))
            current_value = pixel
            count = 1
    runs.append((current_value, count))

    result = []
    current_literal = []

    for value, count in runs:
        if count >= 3:
            while current_literal:
                chunk_length = min(len(current_literal), 127)
                chunk = current_literal[:chunk_length]
                result.append(chunk_length)
                result.extend(chunk)
                current_literal = current_literal[chunk_length:]

            result.append(128 + count)
            result.append(value)
        else:

            current_literal.extend([value] * count)

            while len(current_literal) >= 127:
                chunk = current_literal[:127]
                result.append(127)
                result.extend(chunk)
                current_literal = current_literal[127:]

    while current_literal:
        chunk_length = min(len(current_literal), 127)
        chunk = current_literal[:chunk_length]
        result.append(chunk_length)
        result.extend(chunk)
        current_literal = current_literal[chunk_length:]

    return result


def process_image(image_path, output_txt_path):
    image = cv2.imread(image_path, cv2.IMREAD_GRAYSCALE)

    threshold = 128
    _, binary_image = cv2.threshold(image, threshold, 1, cv2.THRESH_BINARY_INV)

    binary_array = binary_image.flatten().tolist()

    encoded_image = rle(binary_array)

    with open(output_txt_path, "w") as file:
        file.write(" ".join(map(str, encoded_image)))

    print(f"Encoded image saved to {output_txt_path}")


image_path = "img.jpg"
output_path = "result.txt"
process_image(image_path, output_path)
