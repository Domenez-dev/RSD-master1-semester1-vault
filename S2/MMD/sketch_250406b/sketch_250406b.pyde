size(600, 360)
background(255)

# Fish body using arc
fill(255, 120, 0)
arc(95, 80, 100, 100, 0.5, 2.7)

# Fish body using arc
fill(255, 120, 0)
arc(95, 120, 100, 100, 3.55, 5.78 )

# Tail using vertex
fill(255, 100, 0)
beginShape()
vertex(50, 100)
vertex(30, 80)
vertex(30, 120)
endShape(CLOSE)

# Eye
fill(255,255,0)
stroke(255,255,0)
ellipse(125, 90, 5, 5)
