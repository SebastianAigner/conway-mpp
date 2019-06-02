package org.jonnyzzz.lifegame

import java.awt.Color
import java.awt.image.BufferedImage
import java.awt.image.BufferedImage.TYPE_INT_RGB
import kotlin.math.max
import kotlin.math.min

fun Maze3.toImage(width: Int, height: Int): BufferedImage {
  val img = BufferedImage(width, height, TYPE_INT_RGB)

  val ctx = img.graphics
  ctx.color = Color.white
  ctx.fillRect(0, 0, width, height)

  renderToImage(img, Color.BLACK)
  return img
}

fun Maze3.renderToImage(image: BufferedImage, color : Color = Color.BLACK): BufferedImage {
  val stepX = image.width / this.width
  val stepY = image.height / this.height

  val ctx = image.graphics
  ctx.color = color
  forEachAlive { x, y ->
    ctx.fillRect(x * stepX, y * stepY, stepX, stepY)
  }
  return image
}

fun BufferedImage.addAging() = apply {
  for(x in 0 until width) {
    for(y in 0 until height) {
      val p = Color(getRGB(x, y))
      if (p != Color.WHITE) {
        setRGB(x,y, Color(
          min(255, p.red * 5 / 3 + 33 ),
          min(255, p.green * 5 / 3 + 33),
          min(255, p.blue * 5 / 3 + 33)
        ).rgb)
      }
    }
   }
}