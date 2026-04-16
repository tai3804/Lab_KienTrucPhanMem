from django.contrib import admin
from django.urls import path
from core.views import home, trigger_task

urlpatterns = [
    path("admin/", admin.site.urls),
    path("", home),
    path("run-task/", trigger_task),
]
