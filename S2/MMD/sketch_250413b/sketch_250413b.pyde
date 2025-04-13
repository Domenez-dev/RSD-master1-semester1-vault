def setup():
    size(600, 360)
    background(255)
    frameRate(10)  # Optional: you can control frame rate here

pos = 100
def draw():
    global pos 
    # Clear and redraw everything each frame
    background(255)

    loadPixels()
    for i in range (len(pixels)):
        if i % (len(pixels)/10) == 0:
         x = random(255)
         y = random(255)
         z = random(255)
        pixels[i] = color(x,y,z)
    updatePixels()

    pos += 50
    if pos >= 650:
         pos = 0
    # Fish top arc (body)
    fill(255, 120, 0)
    arc(pos-5, 80, 100, 100, 0.5, 2.7)

    # Fish bottom arc (body)
    fill(255, 120, 0)
    arc(pos-5, 120, 100, 100, 3.55, 5.78)

    # Tail using vertex
    fill(255, 100, 0)
    beginShape()
    a = pos-80
    b = pos-50
    vertex(b, 100)
    vertex(a, 80)
    vertex(a, 120)
    endShape(CLOSE)


    # Eye
    fill(255, 255, 0)
    stroke(255, 255, 0)
    ellipse(pos+25, 90, 5, 5)
        

    if frameCount % 10 == 0:
        saveFrame("fish-frame-######.png")
