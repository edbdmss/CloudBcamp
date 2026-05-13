terraform {
  required_providers {
    local = { source = "hashicorp/local", version = "~> 2.0" }
  }
}

variable "student_name" {
  description = "학생 이름"
  type        = string
  default     = "default-student"
}

variable "score" {
  description = "점수"
  type        = number
  default     = 0

  validation {
    condition     = var.score >= 0 && var.score <= 100
    error_message = "score는 0~100 사이여야 함."
  }
}

resource "local_file" "report" {
  content  = "이름: ${var.student_name} / 점수: ${var.score}"
  filename = "${path.module}/report.txt"
}
