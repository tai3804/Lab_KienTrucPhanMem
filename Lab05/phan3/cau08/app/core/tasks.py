from celery import shared_task


@shared_task
def sample_task():
    return "Task executed successfully"
