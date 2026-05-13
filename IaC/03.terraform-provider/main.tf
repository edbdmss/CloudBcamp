# terraform 블럭
terraform {
  required_version = ">= 1.9.0"

  required_providers {
    local = {
      source  = "hashicorp/local"
      version = "~> 2.0"
    }
  }
}

resource "local_file" "first" {
  content  = "내 첫 Terraform 파일"
  filename = "${path.module}/first.txt"
}
