import numpy as np
import matplotlib.pyplot as plt
import math
from matplotlib.font_manager import FontProperties

# import matplotlib
# matplotlib.rc("font", family="WenQuanYi Zen Hei")
# plt.rcParams['font.sans-serif'] = ['WenQuanYi Zen Hei']  # 设置字体
# plt.rcParams["font.sans-serif"]=["SimSun"] #设置字体
# plt.rcParams["axes.unicode_minus"]=False #正常显示负号

# plt.rcParams['font.sans-serif'] = ['WenQuanYi Zen Hei']  # 设置字体
# plt.rcParams['font.sans-serif'] = ['Noto Sans CJK SC']  # 设置字体

# 设置中文字体
# font = FontProperties(fname=r"/usr/share/fonts/noto-cjk/NotoSansC", size=14)
# import matplotlib.pyplot as plt


def normal_distribution(x, mean, sigma):
    # return np.exp(-1*((x-mean)**2)/(2*(sigma**2)))/(math.sqrt(2*np.pi) * sigma)
    return np.exp(-0.5 * ((x - mean) / sigma)** 2)
 
 
mean1, sigma1 = 0, 2
x1 = np.linspace(mean1 - 6*sigma1, mean1 + 6*sigma1, 100)
 
mean2, sigma2 = 2, 2
x2 = np.linspace(mean2 - 6*sigma2, mean2 + 6*sigma2, 100)
mean3, sigma3 = -2, 2
x3 = np.linspace(mean3 - 6*sigma3, mean3 + 6*sigma3, 100)

mean4, sigma4 = 4, 2
x4 = np.linspace(mean4 - 6*sigma4, mean4 + 6*sigma4, 100)
mean5, sigma5 = -4, 2
x5 = np.linspace(mean5 - 6*sigma5, mean5 + 6*sigma5, 100)

mean6, sigma6 = 0, 2
x6 = np.linspace(mean6 - 6*sigma6, mean6 + 6*sigma6, 100)
 
y1 = normal_distribution(x1, mean1, sigma1)
y2 = normal_distribution(x2, mean2, sigma2)
y3 = normal_distribution(x3, mean3, sigma3)
y4 = normal_distribution(x4, mean4, sigma4)
y5 = normal_distribution(x5, mean5, sigma5)
y6 = 1-normal_distribution(x6, mean6, sigma6)
 
# plt.plot(x1, y1, 'r', label='优秀',fontproperties=font)
# plt.plot(x2, y2, 'g', label='良好',fontproperties=font)
# plt.plot(x3, y3, 'g', label='')
# plt.plot(x4, y4, 'b', label='一般',fontproperties=font)
# plt.plot(x5, y5, 'b', label='')
# plt.plot(x6, y6, 'k', label='较差',fontproperties=font)
plt.plot(x1, y1, 'r', label='excellent')
plt.plot(x2, y2, 'g', label='Good')
plt.plot(x3, y3, 'g', label='')
plt.plot(x4, y4, 'b', label='General')
plt.plot(x5, y5, 'b', label='')
plt.plot(x6, y6, 'k', label='Worse')
plt.legend()
plt.grid()
# 设置x轴范围
plt.xlim(-10, 10)

plt.show()
