def rle(image):
    result = []
    j = image[0]
    count = 0
    for i in range(len(image)):
        if image[i] == j:
            count += 1
        else:
            if count <= 2:
                result.extend([j] * count)
            else:
                result.append(128 + count)
                result.append(j)
            j = image[i]
            count = 1

    # Handle the last run
    if count <= 2:
        result.extend([j] * count)
    else:
        result.append(128 + count)
        result.append(j)

    return result


# Example usage
image = [
    0,
    0,
    1,
    1,
    1,
    1,
    1,
    1,
    1,
    0,
    0,
    0,
    1,
    1,
    1,
    1,
    1,
    1,
    0,
    0,
    0,
    1,
    1,
    0,
    1,
    1,
    1,
    0,
    0,
    0,
    0,
    1,
    1,
    1,
    1,
    1,
]

encoded_image = rle(image)
print(image)
print(encoded_image)
