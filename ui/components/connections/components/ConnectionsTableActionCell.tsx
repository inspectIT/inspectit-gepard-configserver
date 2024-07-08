import { buttonVariants } from "@/components/shared/shadcn/button";
import { UUID } from "crypto";
import Link from "next/link";

interface connectionTableActionsProps {
  id: UUID;
}
export default function ConnectionTableActionCell({
  id,
}: connectionTableActionsProps) {
  return (
    <Link
      className={buttonVariants({ variant: "outline" })}
      href={`agent-settings/?connection=${id}`}
    >
      Open
    </Link>
  );
}
