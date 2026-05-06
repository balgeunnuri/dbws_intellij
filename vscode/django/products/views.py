from django.shortcuts import render
from .models import Product

# Create your views here.
# views.py에서 상품 목록 가져오기
def product_list(request):
    products = Product.objects.all()  # 모든 상품 가져오기
    return render(request, 'products/product_list.html', {'products': products})