from django.http import JsonResponse
from .tasks import sample_task


def home(_request):
    return JsonResponse({"message": "Django + Celery + Redis is running"})


def trigger_task(_request):
    task = sample_task.delay()
    return JsonResponse({"task_id": task.id, "status": "queued"})
